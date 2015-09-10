load("nashorn:mozilla_compat.js");
load("fx:controls.js");
importClass(java.lang.System);
function handleSubmitButtonAction() {
    actiontarget.setText("Calling the JavaScript");
    System.out.println('I am java println from javascript');

    var alert = new Alert(Alert.AlertType.ERROR, 'Error message from js', ButtonType.OK);
    alert.showAndWait();
}
