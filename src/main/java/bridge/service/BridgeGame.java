package bridge.service;

import bridge.domain.Bridge;
import bridge.service.constant.GameStatus;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private int attemptCount;
    private int panelOrder;
    private GameStatus status;
    private Bridge bridge;

    public BridgeGame (Bridge bridge) {
        attemptCount = 0;
        panelOrder = 0;
        status = GameStatus.PLAYING;
        this.bridge = bridge;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(String userChoice) {
        increaseOrder();
        increaseAttemptCount();
        boolean isUserChoiceCorrect = bridge.isCorrectPanel(panelOrder, userChoice);
        modifyStatusAfterChoice(isUserChoiceCorrect);
        return  isUserChoiceCorrect;
    }

    private void modifyStatusAfterChoice(boolean isUserChoiceCorrect) {
        if (isUserChoiceCorrect && bridge.isEnd(panelOrder)) {
            status = GameStatus.SUCCESS;
        }

        if (!isUserChoiceCorrect) {
            status = GameStatus.FAIL;
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        decreaseOrder();
    }

    private void increaseAttemptCount() {
        attemptCount++;
    }

    private void increaseOrder() {
        panelOrder++;
    }

    private void decreaseOrder() {
        panelOrder--;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public int getPanelOrder() {
        return panelOrder;
    }

    public GameStatus getStatus() {
        return status;
    }
}
