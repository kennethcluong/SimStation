package mvc;

import java.io.Serial;
import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String fileName = null;
    private Boolean unsavedChanges = false;

    // Getters
    public String getFileName() { return fileName; }

    public Boolean getUnsavedChanges() { return unsavedChanges; }

    // Setters
    public void setFileName(String newFileName) { this.fileName = newFileName; }

    public void setUnsavedChanges(Boolean unsavedChanges) { this.unsavedChanges = unsavedChanges; }

    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }
}
