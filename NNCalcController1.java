import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Ayub Abdi
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model, NNCalcView view) {

        /*
         * Get alias to bottom from model
         */
        NaturalNumber topNum = model.top();
        NaturalNumber bottomNum = model.bottom();

        /*
         * Update view for the availabilities of operations subtract, divide,
         * power, and root
         */
        view.updateSubtractAllowed(model.bottom().compareTo(topNum) <= 0);
        view.updateDivideAllowed(!model.bottom().isZero());
        view.updatePowerAllowed(model.bottom().compareTo(INT_LIMIT) <= 0);
        view.updateRootAllowed(model.bottom().compareTo(INT_LIMIT) <= 0
                && model.bottom().compareTo(TWO) >= 0);

        /*
         * Update view for top and bottom displays
         */
        view.updateTopDisplay(topNum);
        view.updateBottomDisplay(bottomNum);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.copyFrom(bottomNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.add(bottomNum);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.subtract(bottomNum);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.multiply(bottomNum);
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        NaturalNumber remainder = topNum.divide(bottomNum);

        bottomNum.copyFrom(topNum);
        topNum.transferFrom(remainder);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.power(bottomNum.toInt());
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber topNum = this.model.top();
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        topNum.root(bottomNum.toInt());
        bottomNum.transferFrom(topNum);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bottomNum = this.model.bottom();

        /*
         * Update model in response to this event
         */
        bottomNum.multiplyBy10(digit);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

}
