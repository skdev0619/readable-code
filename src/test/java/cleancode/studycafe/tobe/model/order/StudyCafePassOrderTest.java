package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {

    @DisplayName("선택한 이용권의 주문 가격을 알 수 있다")
    @Test
    void defaultTotalPrice(){
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
            StudyCafePassType.HOURLY,
            2,
            4000,
            0
        );

        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass,null);

        assertThat(order.getTotalPrice()).isEqualTo(4000);
    }

    @DisplayName("사물함이 포함된 고정석의 주문 가격을 알 수 있다")
    @Test
    void totalPriceWithLocker(){
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
            StudyCafePassType.FIXED,
            4,
            250000,
            0.1
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass,lockerPass);
        //when
        int totalPrice = order.getTotalPrice();
        //then
        assertThat(totalPrice).isEqualTo(235000);
    }

    @DisplayName("2주권 이상 결제 시 총 주문 가격에서 10% 할인된다")
    @Test
    void discountOverTwoWeeks(){
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
            StudyCafePassType.FIXED,
            4,
            250000,
            0.1
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass,lockerPass);
        //when
        int totalPrice = order.getTotalPrice();
        //then
        assertThat(totalPrice).isEqualTo(235000);
    }

    @DisplayName("12주권 결제 시 총 주문 가격에서 15% 할인된다")
    @Test
    void discountOverTwelveWeeks(){
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(
            StudyCafePassType.FIXED,
            12,
            700000,
            0.15
        );
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass,lockerPass);
        //when
        int totalPrice = order.getTotalPrice();
        //then
        assertThat(totalPrice).isEqualTo(625000);
    }
}
