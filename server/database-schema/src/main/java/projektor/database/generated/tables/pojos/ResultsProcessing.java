/*
 * This file is generated by jOOQ.
 */
package projektor.database.generated.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ResultsProcessing implements Serializable {

    private static final long serialVersionUID = 1574490091;

    private String    publicId;
    private String    status;
    private String    errorMessage;
    private Timestamp createdTimestamp;

    public ResultsProcessing() {}

    public ResultsProcessing(ResultsProcessing value) {
        this.publicId = value.publicId;
        this.status = value.status;
        this.errorMessage = value.errorMessage;
        this.createdTimestamp = value.createdTimestamp;
    }

    public ResultsProcessing(
        String    publicId,
        String    status,
        String    errorMessage,
        Timestamp createdTimestamp
    ) {
        this.publicId = publicId;
        this.status = status;
        this.errorMessage = errorMessage;
        this.createdTimestamp = createdTimestamp;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public ResultsProcessing setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public ResultsProcessing setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public ResultsProcessing setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Timestamp getCreatedTimestamp() {
        return this.createdTimestamp;
    }

    public ResultsProcessing setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ResultsProcessing other = (ResultsProcessing) obj;
        if (publicId == null) {
            if (other.publicId != null)
                return false;
        }
        else if (!publicId.equals(other.publicId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (errorMessage == null) {
            if (other.errorMessage != null)
                return false;
        }
        else if (!errorMessage.equals(other.errorMessage))
            return false;
        if (createdTimestamp == null) {
            if (other.createdTimestamp != null)
                return false;
        }
        else if (!createdTimestamp.equals(other.createdTimestamp))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.publicId == null) ? 0 : this.publicId.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.errorMessage == null) ? 0 : this.errorMessage.hashCode());
        result = prime * result + ((this.createdTimestamp == null) ? 0 : this.createdTimestamp.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultsProcessing (");

        sb.append(publicId);
        sb.append(", ").append(status);
        sb.append(", ").append(errorMessage);
        sb.append(", ").append(createdTimestamp);

        sb.append(")");
        return sb.toString();
    }
}