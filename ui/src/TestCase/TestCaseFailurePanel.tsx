import * as React from "react";
import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import ExpansionPanelActions from "@material-ui/core/ExpansionPanelActions";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import Button from "@material-ui/core/Button";
import Divider from "@material-ui/core/Divider";
import { makeStyles } from "@material-ui/core/styles";
import { TestCase } from "../model/TestRunModel";
import { Link } from "@reach/router";
import { Typography } from "@material-ui/core";

const useStyles = makeStyles(() => ({
  panelActions: {
    justifyContent: "flex-start"
  }
}));

interface TestCaseFailurePanelProps {
  testCase: TestCase;
  publicId: String;
}

const TestCaseFailurePanel = ({
  testCase,
  publicId
}: TestCaseFailurePanelProps) => {
  const classes = useStyles({});
  const testCaseIdentifier = `${testCase.testSuiteIdx}-${testCase.idx}`;

  return (
    <ExpansionPanel
      defaultExpanded
      data-testid={`test-case-summary-${testCaseIdentifier}`}
    >
      <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
        <Typography variant="subtitle2" data-testid="test-case-title">
          {testCase.packageName}.{testCase.className} {testCase.name}
        </Typography>
      </ExpansionPanelSummary>
      <ExpansionPanelDetails>
        {testCase.failure != null && (
          <div>
            <pre>{testCase.failure.failureMessage}</pre>
          </div>
        )}
      </ExpansionPanelDetails>
      <Divider />
      <ExpansionPanelActions className={classes.panelActions}>
        {!testCase.passed && (
          <Button>
            <Link
              to={`/tests/${publicId}/suite/${testCase.testSuiteIdx}/case/${testCase.idx}/failure`}
              data-testid={`test-case-summary-failure-link-${testCaseIdentifier}`}
            >
              Failure Details
            </Link>
          </Button>
        )}
        {testCase.hasSystemOut && (
          <Button>
            <Link
              to={`/tests/${publicId}/suite/${testCase.testSuiteIdx}/case/${testCase.idx}/systemOut`}
              data-testid={`test-case-summary-system-out-link-${testCaseIdentifier}`}
            >
              System Out
            </Link>
          </Button>
        )}
        {testCase.hasSystemErr && (
          <Button>
            <Link
              to={`/tests/${publicId}/suite/${testCase.testSuiteIdx}/case/${testCase.idx}/systemErr`}
              data-testid={`test-case-summary-system-err-link-${testCaseIdentifier}`}
            >
              System Err
            </Link>
          </Button>
        )}
      </ExpansionPanelActions>
    </ExpansionPanel>
  );
};

export default TestCaseFailurePanel;