import "@testing-library/jest-dom/extend-expect";
import React from "react";
import { render } from "@testing-library/react";
import { TestCase } from "../../model/TestRunModel";
import TestCaseFailurePanel from "../TestCaseFailurePanel";

describe("TestCaseFailurePanel", () => {
  it("should render failure details link when the test case failed", () => {
    const testCase: TestCase = {
      idx: 1,
      testSuiteIdx: 2,
      name: "Test Case",
      packageName: "",
      className: "",
      duration: 1.2,
      passed: false,
      skipped: false,
      hasSystemOut: false,
      hasSystemErr: false,
      failure: null
    };

    const { queryByTestId } = render(
      <TestCaseFailurePanel testCase={testCase} publicId="12345" />
    );

    expect(queryByTestId("test-case-summary-failure-link-2-1")).not.toBeNull();

    expect(queryByTestId("test-case-summary-system-out-link-2-1")).toBeNull();
    expect(queryByTestId("test-case-summary-system-err-link-2-1")).toBeNull();
  });

  it("should not render failure details link when the test case passed", () => {
    const testCase: TestCase = {
      idx: 1,
      testSuiteIdx: 2,
      name: "Test Case",
      packageName: "",
      className: "",
      duration: 1.2,
      passed: true,
      skipped: false,
      hasSystemOut: false,
      hasSystemErr: false,
      failure: null
    };

    const { queryByTestId } = render(
      <TestCaseFailurePanel testCase={testCase} publicId="12345" />
    );

    expect(queryByTestId("test-case-summary-failure-link-2-1")).toBeNull();

    expect(queryByTestId("test-case-summary-system-out-link-2-1")).toBeNull();
    expect(queryByTestId("test-case-summary-system-err-link-2-1")).toBeNull();
  });

  it("should render system out link when the test case has system out", () => {
    const testCase: TestCase = {
      idx: 1,
      testSuiteIdx: 2,
      name: "Test Case",
      packageName: "",
      className: "",
      duration: 1.2,
      passed: false,
      skipped: false,
      hasSystemOut: true,
      hasSystemErr: false,
      failure: null
    };

    const { queryByTestId } = render(
      <TestCaseFailurePanel testCase={testCase} publicId="12345" />
    );

    expect(
      queryByTestId("test-case-summary-system-out-link-2-1")
    ).not.toBeNull();
    expect(queryByTestId("test-case-summary-system-err-link-2-1")).toBeNull();
  });

  it("should render system err link when the test case has system err", () => {
    const testCase: TestCase = {
      idx: 1,
      testSuiteIdx: 2,
      name: "Test Case",
      packageName: "",
      className: "",
      duration: 1.2,
      passed: false,
      skipped: false,
      hasSystemOut: false,
      hasSystemErr: true,
      failure: null
    };

    const { queryByTestId } = render(
      <TestCaseFailurePanel testCase={testCase} publicId="12345" />
    );

    expect(queryByTestId("test-case-summary-system-out-link-2-1")).toBeNull();
    expect(
      queryByTestId("test-case-summary-system-err-link-2-1")
    ).not.toBeNull();
  });

  it("should render both system out and err links when the test case has both", () => {
    const testCase: TestCase = {
      idx: 1,
      testSuiteIdx: 2,
      name: "Test Case",
      packageName: "",
      className: "",
      duration: 1.2,
      passed: false,
      skipped: false,
      hasSystemOut: true,
      hasSystemErr: true,
      failure: null
    };

    const { queryByTestId } = render(
      <TestCaseFailurePanel testCase={testCase} publicId="12345" />
    );

    expect(
      queryByTestId("test-case-summary-system-out-link-2-1")
    ).not.toBeNull();
    expect(
      queryByTestId("test-case-summary-system-err-link-2-1")
    ).not.toBeNull();
  });
});