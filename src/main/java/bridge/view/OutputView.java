package bridge.view;

import bridge.constant.Directions;
import bridge.view.constant.BridgeStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<String> bridge, int order, boolean isUserChoiceWrong) {
        List<List<String>> bridgeLines = new ArrayList<>();
        Arrays.stream(Directions.values())
                .forEach(direction -> bridgeLines.add(collectPanelsOfLine(bridge, order, direction.getSymbol())));
        if (isUserChoiceWrong) {
            putFailureElement(bridgeLines, order);
        }
        bridgeLines.stream().map(this::styleBridgeLine)
                .forEach(System.out::println);
    }

    private List<String> collectPanelsOfLine (List<String> bridge, int order, String line) {
        return bridge.stream().limit(order)
                .map(panel -> createPanelElement(panel, line))
                .collect(Collectors.toList());
    }

    private String createPanelElement (String panel, String line) {
        if (panel.equals(line)) {
            return BridgeStyle.CORRECT_PANEL.get();
        }
        return BridgeStyle.EMPTY_PANEL.get();
    }

    private void putFailureElement (List<List<String>> bridgeLines, int order) {
        for (List<String> line : bridgeLines) {
            putFailureElementToLine(line, order);
        }
    }

    private void putFailureElementToLine (List<String> line, int order) {
        int lastPanelIndex = --order;
        String lastPanel = line.get(lastPanelIndex);
        if (lastPanel.equals(BridgeStyle.EMPTY_PANEL.get())) {
            line.set(lastPanelIndex, BridgeStyle.WRONG_PANEL.get());
            return;
        }
        if (lastPanel.equals(BridgeStyle.CORRECT_PANEL.get())) {
            line.set(lastPanelIndex, BridgeStyle.EMPTY_PANEL.get());
        }
    }

    private String styleBridgeLine (List<String> line) {
        return line.stream().collect(Collectors.joining(
                BridgeStyle.DELIMITER.get(),
                BridgeStyle.START.get(),
                BridgeStyle.END.get()
        ));
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }
}
