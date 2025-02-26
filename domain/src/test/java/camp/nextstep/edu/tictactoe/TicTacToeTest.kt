package camp.nextstep.edu.tictactoe

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class TicTacToeTest {
    private lateinit var ticTacToe: TicTacToe

    @Before
    fun setUp() {
        ticTacToe = TicTacToe()
    }

    @Test
    fun `처음 시작에서 (0, 0)에 값을 넣는 경우 X가 출력되어야 한다`() {
        // when
        ticTacToe.put(0, 0)
        //then
        val actual = ticTacToe.getTicTacToeCell(0, 0)
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `(0, 0)에 값이 있는데 (0, 0)에 값을 넣는 경우 기존에 있던 값이 출력되어야 한다`() {
        // given
        ticTacToe.put(0, 0)
        val actual1 = ticTacToe.getTicTacToeCell(0, 0)

        // when
        ticTacToe.put(0, 0)

        //then
        val actual2 = ticTacToe.getTicTacToeCell(0, 0)
        Truth.assertThat(actual2).isEqualTo(actual1)
    }

    @Test
    fun `(0, 0)에 값을 넣고 (0, 1)에 값을 넣는 각각 X, O가 출력되어야 한다`() {

        // when
        ticTacToe.put(0, 0)
        ticTacToe.put(0, 1)

        //then
        val actual1 = ticTacToe.getTicTacToeCell(0, 0)
        val actual2 = ticTacToe.getTicTacToeCell(0, 1)
        Truth.assertThat(actual1).isEqualTo(OX.X)
        Truth.assertThat(actual2).isEqualTo(OX.O)
    }


    @Test
    fun `값이 있는 게임에 초기화를 하는 경우 모든 셀이 비어있어야 한다`() {
        // given
        val empty = Array<Array<OX?>>(3) { Array(3) { null } }

        ticTacToe.put(0, 0)
        ticTacToe.put(0, 1)
        ticTacToe.put(0, 2)

        ticTacToe.put(1, 0)
        ticTacToe.put(1, 1)
        ticTacToe.put(2, 1)

        ticTacToe.put(1, 2)
        ticTacToe.put(2, 2)
        ticTacToe.put(2, 0)

        // when
        ticTacToe.reset()

        //then
        val actual = ticTacToe.getAllCell()

        Truth.assertThat(actual).isEqualTo(empty)
    }

    @Test
    fun `모든 값이 채워져 있고, 이긴 상황이 없는 경우 비겨야 한다`() {
        // given
        ticTacToe.put(0, 0)
        ticTacToe.put(0, 1)
        ticTacToe.put(0, 2)
        ticTacToe.put(1, 0)
        ticTacToe.put(1, 1)
        ticTacToe.put(2, 2)
        ticTacToe.put(2, 1)
        ticTacToe.put(2, 0)
        ticTacToe.put(1, 2)

        // when
        val actual = ticTacToe.getGameStatus()

        //then
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.DRAW)
    }

    @Test
    fun `Row로 이긴 경우 draw 함수 같이 체크`() {
        // given
        ticTacToe.put(0, 0)
        ticTacToe.put(1, 0)
        ticTacToe.put(0, 1)
        ticTacToe.put(1, 1)
        ticTacToe.put(0, 2)

        // when
        val actual = ticTacToe.getGameStatus()

        //then
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.X_WIN)
    }

    @Test
    fun `Column로 이긴 경우 draw 함수 같이 체크`() {
        // given
        ticTacToe.put(0, 0)
        ticTacToe.put(0, 1)
        ticTacToe.put(1, 0)
        ticTacToe.put(1, 1)
        ticTacToe.put(2, 0)

        // when
        val actual = ticTacToe.getGameStatus()

        //then
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.X_WIN)
    }

    @Test
    fun `Diagonal로 이긴 경우 draw 함수 같이 체크`() {
        // given
        ticTacToe.put(0, 0)
        ticTacToe.put(0, 1)
        ticTacToe.put(1, 1)
        ticTacToe.put(1, 2)
        ticTacToe.put(2, 2)

        // when
        val actual = ticTacToe.getGameStatus()

        //then
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.X_WIN)
    }

    @Test
    fun `Reverse Diagonal로 이긴 경우 draw 함수 같이 체크`() {

        // given
        ticTacToe.put(2, 0)
        ticTacToe.put(0, 1)
        ticTacToe.put(1, 1)
        ticTacToe.put(1, 2)
        ticTacToe.put(0, 2)

        // when
        val actual = ticTacToe.getGameStatus()

        //then
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.X_WIN)
    }
}