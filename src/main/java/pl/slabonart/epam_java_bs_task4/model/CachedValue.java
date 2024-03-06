package pl.slabonart.epam_java_bs_task4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CachedValue {

    private int attempts;
    private LocalDateTime blockedTimestamp;
    private LocalDateTime blockedUntilTimestamp;

    public void registerAttempt() {
        this.attempts++;
    }
}
