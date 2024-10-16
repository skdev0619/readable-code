package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassesTest {

    @DisplayName("내가 선택한 기간과 동일한 기간의 사물함을 조회한다")
    @Test
    void findLockerPassBySeatPass(){
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
            StudyCafePassType.FIXED,
            4,
            250000,
            0.1
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(lockerPass));
        //when
        StudyCafeLockerPass lockerPassBySeatPass = lockerPasses.findLockerPassBy(seatPass).get();
        //then
        assertThat(lockerPassBySeatPass).isEqualTo(lockerPass);
    }

}
