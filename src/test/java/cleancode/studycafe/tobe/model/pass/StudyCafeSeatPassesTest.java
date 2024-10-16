package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassesTest {
    
    @DisplayName("내가 선택한 이용권의 목록 조회가 가능하다")
    @Test
    void findPassByPassType(){
        //given
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        StudyCafeSeatPasses seatPasses = seatPassProvider.getSeatPasses();
        //when
        List<StudyCafeSeatPass> studyCafeSeatPasses = seatPasses.findPassBy(StudyCafePassType.FIXED);
        //then
        assertThat(studyCafeSeatPasses).hasSize(2);
        assertThat(studyCafeSeatPasses.get(0).getPassType()).isEqualTo(StudyCafePassType.FIXED);
        assertThat(studyCafeSeatPasses.get(1).getPassType()).isEqualTo(StudyCafePassType.FIXED);
    }

}
