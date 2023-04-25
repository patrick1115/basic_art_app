package controller;

import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;
import main.CommandHistory;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvasBase paintCanvas; 

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvasBase paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas; 
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().execute());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().execute());
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand().execute());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(applicationState, paintCanvas).execute());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand().execute());
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand(paintCanvas).execute());
        uiModule.addEvent(EventName.UNGROUP, () -> new UnGroupCommand().execute());
    }

}
