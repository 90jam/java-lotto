package nextstep.step2.view;

import nextstep.step2.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoTicketViewTest {
	@Test
	@DisplayName("로또 티켓을 출력한다.")
	public void lottoTicketPrintTest() {
		LottoTicketView.printLottoTickets(getMockLottos());
	}

	private List<Lotto> getMockLottos() {
		Lotto lotto1 = Lotto.of("1,2,3,4,5,6");
		Lotto lotto2 = Lotto.of("7,8,9,10,11,12");
		return Arrays.asList(lotto1,lotto2);
	}
}
