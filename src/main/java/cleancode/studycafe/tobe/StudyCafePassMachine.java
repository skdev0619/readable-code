package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            showGuideMessage();
            StudyCafePass selectedPass = getSelectedPass();

            Optional<StudyCafeLockerPass> lockerPass = selectLockerPass(selectedPass);

            lockerPass.ifPresentOrElse(
                    pass -> outputHandler.showPassOrderSummary(selectedPass, pass),
                    () -> outputHandler.showPassOrderSummary(selectedPass)
            );

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass getSelectedPass() {
        StudyCafePassType studyCafePassType = getStudyCafePassType();

        List<StudyCafePass> passes = findPassesFrom(studyCafePassType);
        showPasses(passes);

        StudyCafePass selectedPass = getSelectPass(passes);
        return selectedPass;
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
        if(selectedPass.canNotUseLocker()){
            return Optional.empty();
        }

        StudyCafeLockerPass lockerPassCandidate = findLockerPassCandidateFrom(selectedPass);

        if (lockerPassCandidate != null) {
            outputHandler.askLockerPass(lockerPassCandidate);
            boolean isLockerSelected = inputHandler.getLockerSelection();

            if(isLockerSelected){
                return Optional.of(lockerPassCandidate);
            }
        }
        return Optional.empty();
    }

    private StudyCafeLockerPass findLockerPassCandidateFrom(StudyCafePass pass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.stream()
                .filter(pass::isSameDurationType)
                .findFirst()
                .orElse(null);
    }

    private void showPasses(List<StudyCafePass> hourlyPasses) {
        outputHandler.showPassListForSelection(hourlyPasses);
    }

    private List<StudyCafePass> findPassesFrom(StudyCafePassType passType) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(passType))
            .toList();
    }

    private void showGuideMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private StudyCafePassType getStudyCafePassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass getSelectPass(List<StudyCafePass> hourlyPasses) {
        return inputHandler.getSelectPass(hourlyPasses);
    }

}
