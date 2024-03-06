package pl.slabonart.epam_java_bs_task4.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import pl.slabonart.epam_java_bs_task4.model.CachedValue;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class LoginAttemptService {

    public static final int MAX_ATTEMPTS = 3;

    public static final int BLOCK_DURATION_IN_SEC = 10;

    private final LoadingCache<String, CachedValue> attemptsCache;

    public LoginAttemptService() {
        attemptsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(BLOCK_DURATION_IN_SEC, TimeUnit.SECONDS)
                .build(
                        new CacheLoader<>() {
                            @Override
                            public CachedValue load(String key) throws Exception {
                                return new CachedValue(0, null, null);
                            }
                        }
                );
    }

    public void loginFailed(final String key) {
        try {
            var cachedValue = attemptsCache.get(key);
            cachedValue.registerAttempt();

            if (isBlocked(key) && cachedValue.getBlockedTimestamp() == null) {
                cachedValue.setBlockedTimestamp(LocalDateTime.now());
                cachedValue.setBlockedUntilTimestamp(cachedValue.getBlockedTimestamp().plusSeconds(BLOCK_DURATION_IN_SEC));
            }

            attemptsCache.put(key, cachedValue);
        } catch (final ExecutionException e) {
            log.error("Failed to get cached value", e);
        }
    }

    public void loginSuccess(String key) {
        CachedValue cachedValue = new CachedValue(0, null, null);
        attemptsCache.put(key, cachedValue);
    }

    public boolean isBlocked(final String key) {
        try {
            return attemptsCache.get(key).getAttempts() >= MAX_ATTEMPTS;
        } catch (final ExecutionException e) {
            return false;
        }
    }

    public CachedValue getCachedValue(String key) {
        try {
            return attemptsCache.get(key);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
