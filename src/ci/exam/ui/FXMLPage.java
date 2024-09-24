package ci.exam.ui;

import java.net.URL;

public enum FXMLPage {
    HOME("/ci/exam/ui/fxml/Home.fxml"),
    VIEW("/ci/exam/ui/fxml/ViewResult.fxml");
	

    private final String location;

    FXMLPage(String location) {
        this.location = location;
    }

    public URL getPage() {
        return  getClass().getResource(location) ;
    }
}
