package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassTypeTest {

    @DisplayName("락커는 고정석만 이용 가능하다")
    @Test
    void isLockerType(){
        assertThat(StudyCafePassType.FIXED.isLockerType()).isTrue();
        assertThat(StudyCafePassType.HOURLY.isLockerType()).isFalse();
        assertThat(StudyCafePassType.WEEKLY.isLockerType()).isFalse();
    }

}
