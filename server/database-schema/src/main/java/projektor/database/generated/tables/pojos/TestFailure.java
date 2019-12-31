/*
 * This file is generated by jOOQ.
 */
package projektor.database.generated.tables.pojos;


import java.io.Serializable;

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
public class TestFailure implements Serializable {

    private static final long serialVersionUID = -1628658101;

    private Long   id;
    private Long   testCaseId;
    private String failureMessage;
    private String failureType;
    private String failureText;

    public TestFailure() {}

    public TestFailure(TestFailure value) {
        this.id = value.id;
        this.testCaseId = value.testCaseId;
        this.failureMessage = value.failureMessage;
        this.failureType = value.failureType;
        this.failureText = value.failureText;
    }

    public TestFailure(
        Long   id,
        Long   testCaseId,
        String failureMessage,
        String failureType,
        String failureText
    ) {
        this.id = id;
        this.testCaseId = testCaseId;
        this.failureMessage = failureMessage;
        this.failureType = failureType;
        this.failureText = failureText;
    }

    public Long getId() {
        return this.id;
    }

    public TestFailure setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTestCaseId() {
        return this.testCaseId;
    }

    public TestFailure setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
        return this;
    }

    public String getFailureMessage() {
        return this.failureMessage;
    }

    public TestFailure setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
        return this;
    }

    public String getFailureType() {
        return this.failureType;
    }

    public TestFailure setFailureType(String failureType) {
        this.failureType = failureType;
        return this;
    }

    public String getFailureText() {
        return this.failureText;
    }

    public TestFailure setFailureText(String failureText) {
        this.failureText = failureText;
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
        final TestFailure other = (TestFailure) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (testCaseId == null) {
            if (other.testCaseId != null)
                return false;
        }
        else if (!testCaseId.equals(other.testCaseId))
            return false;
        if (failureMessage == null) {
            if (other.failureMessage != null)
                return false;
        }
        else if (!failureMessage.equals(other.failureMessage))
            return false;
        if (failureType == null) {
            if (other.failureType != null)
                return false;
        }
        else if (!failureType.equals(other.failureType))
            return false;
        if (failureText == null) {
            if (other.failureText != null)
                return false;
        }
        else if (!failureText.equals(other.failureText))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.testCaseId == null) ? 0 : this.testCaseId.hashCode());
        result = prime * result + ((this.failureMessage == null) ? 0 : this.failureMessage.hashCode());
        result = prime * result + ((this.failureType == null) ? 0 : this.failureType.hashCode());
        result = prime * result + ((this.failureText == null) ? 0 : this.failureText.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TestFailure (");

        sb.append(id);
        sb.append(", ").append(testCaseId);
        sb.append(", ").append(failureMessage);
        sb.append(", ").append(failureType);
        sb.append(", ").append(failureText);

        sb.append(")");
        return sb.toString();
    }
}