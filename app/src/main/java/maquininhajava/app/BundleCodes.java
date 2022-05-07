package maquininhajava.app;

/**
 * Created by sebad on 3/20/15.
 * Modified by PabloGallazzi on 10/07/16.
 */

public interface BundleCodes {

    //To start the operation.
    /**
     * This is the amount to be charged, it must be a double.
     */
    String AMOUNT = "amount";
    /**
     * This is the description to be seen at MercadoPago, it must be a String.
     */
    String DESCRIPTION = "description";
    /**
     * Send this if you want to be notified that the buyer cancelled the payment in the middle.
     * The accepted values are true and false, the default is set to false
     */
    String DISABLE_BACK_BUTTON = "disable_back_button";

    String CARD_TYPE = "card_type";
    /**
     * Specify the amount of installments must be an int
     */
    String INSTALLMENTS = "installments";
    /**
     * Specify your MercadoPago's Application id. Can be null
     */
    String APP_ID = "client_id";
    /**
     * Specify your MercadoPago's Application secret. Can be null
     */
    String APP_SECRET = "client_secret";
    /**
     * Specify how much are you going to charge. This is NOT a percentage,
     * should be a number smaller than the value in AMOUNT. Can be null
     */
    String APP_FEE = "application_fee";
    /**
     * Specify the sponsor_id to be used. If sent it can't be null
     */
    String SPONSOR_ID = "sponsor_id";
    /**
     * Specify the notification_url to be used. If sent it can't be null
     */
    String NOTIFICATION_URL = "notification_url";

    /**
     * Specifies the payer email to be pre loaded into the send email receipt view. If sent it can't be null
     */
    String PAYER_EMAIL = "payer_email";

    /**
     * Specifies the collector email to be used. If sent it can't be null
     */
    String COLLECTOR_ID = "collector_id";

    /**
     * Specify Kiosk mode. Which means the app will have less functionality to prevent access to the configuration.
     */
    String IS_KIOSK = "is_kiosk";

    /**
     * Specifies the payer identification to be pre loaded into the identification view. If sent it can't be null. This
     * parameter is only needed for Argentina
     */
    String IDENTIFICATION = "identification";

    /**
     * Specify the external_reference to be used. If sent it can't be null
     */
    String EXTERNAL_REFERENCE = "external_reference";

    //For the return of the operation.
    /**
     * In case you are coming from URL based integration,
     * this are the URL's the app will call to end the flow.
     */
    String URL_SUCCESS = "success_url";
    String URL_FAIL = "fail_url";

    String RESULT_STATUS = "result_status";

    String PAYMENT_ID = "payment_id";
    /**
     * Specifies the trunc name of the person being charged
     */
    String TRUNC_CARD_HOLDER = "trunc_card_holder";
    /**
     * This two only comes when the payment is rejected.
     */
    String ERROR = "error";
    String ERROR_DETAIL = "error_detail";

    /**
     * This is for the status receiver
     */
    String STATUS = "STATUS";
}