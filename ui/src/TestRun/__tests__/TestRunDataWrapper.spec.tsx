import "@testing-library/jest-dom/extend-expect";
import React from "react";
import MockAdapter from "axios-mock-adapter";
import { render, wait } from "@testing-library/react";
import { TestRunSummary } from "../../model/TestRunModel";
import {
  axiosInstance,
  axiosInstanceWithoutCache
} from "../../service/TestRunService";
import TestRunDataWrapper from "../TestRunDataWrapper";

describe("TestRunDataWrapper", () => {
  let mockAxios;
  let mockAxiosWithoutCache;

  beforeEach(() => {
    // @ts-ignore
    mockAxios = new MockAdapter(axiosInstance);

    // @ts-ignore
    mockAxiosWithoutCache = new MockAdapter(axiosInstanceWithoutCache);
  });

  afterEach(() => {
    mockAxios.restore();
    mockAxiosWithoutCache.restore();
  });

  it("should render wrapper when fetch completes successfully", async () => {
    const publicId = "12345";
    const testRunSummary = {
      id: publicId,
      totalTestCount: 4,
      totalPassingCount: 2,
      totalSkippedCount: 1,
      totalFailureCount: 1,
      passed: false,
      cumulativeDuration: 10.0,
      averageDuration: 2.5,
      slowestTestCaseDuration: 5.0
    } as TestRunSummary;

    mockAxios
      .onGet(`http://localhost:8080/run/${publicId}/summary`)
      .reply(200, testRunSummary);

    mockAxios
      .onGet(`http://localhost:8080/run/${publicId}/cases/failed`)
      .reply(200, []);

    const { getByTestId, queryByTestId } = render(
      <TestRunDataWrapper publicId={publicId} />
    );

    await wait(() => getByTestId("test-run-menu-wrapper"));

    expect(queryByTestId("test-run-menu-wrapper")).not.toBeNull();
    expect(queryByTestId("loading-section-error")).toBeNull();
  });

  it("should render processing message when fetching test summary fails and results are still processing", async () => {
    const publicId = "12345";

    mockAxios
      .onGet(`http://localhost:8080/run/${publicId}/summary`)
      .reply(404, {});

    mockAxiosWithoutCache
      .onGet(`http://localhost:8080/results/${publicId}/status`)
      .reply(200, { status: "PROCESSING" });

    const { getByTestId, queryByTestId } = render(
      <TestRunDataWrapper publicId={publicId} />
    );

    await wait(() => getByTestId("results-still-processing"));

    expect(queryByTestId("results-still-processing")).not.toBeNull();
    expect(queryByTestId("test-run-menu-wrapper")).toBeNull();
  });
});
