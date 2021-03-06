const axios = require("axios");
const MockAdapter = require("axios-mock-adapter");
const { collectResults, sendResults } = require("../publish");

describe("Projektor publisher", () => {
  let mockAxios;

  beforeEach(() => {
    mockAxios = new MockAdapter(axios);
  });

  afterEach(() => {
    mockAxios.restore();
  });

  it("should gather results from one directory and send it to server", done => {
    const fileGlob = "src/__tests__/resultsDir1/*.xml";
    const serverUrl = "http://localhost:8080";

    mockAxios
      .onPost("http://localhost:8080/results")
      .reply(200, { id: "ABC123", uri: "/tests/ABC123" });

    const resultsBlob = collectResults([fileGlob]);

    sendResults(resultsBlob, serverUrl).then(respData => {
      expect(respData.id).toBe("ABC123");
      expect(respData.uri).toBe("/tests/ABC123");

      expect(mockAxios.history.post.length).toBe(1);

      const postData = mockAxios.history.post[0].data;

      expect(postData).toContain("resultsDir1-results1");
      expect(postData).toContain("resultsDir1-results2");
      expect(postData).not.toContain("resultsDir2-results2");
      expect(postData).not.toContain("resultsDir2-results2");

      done();
    });
  });

  it("should gather results from glob that matches multiple directories", () => {
    const fileGlob = "src/__tests__/resultsDir*/*.xml";

    const resultsBlob = collectResults([fileGlob]);

    expect(resultsBlob).toContain("resultsDir1-results1");
    expect(resultsBlob).toContain("resultsDir1-results2");
    expect(resultsBlob).toContain("resultsDir1-results1");
    expect(resultsBlob).toContain("resultsDir2-results2");
  });

  it("should gather results from glob that matches single file", () => {
    const fileGlob = "src/__tests__/resultsDir2/results2.xml";

    const resultsBlob = collectResults([fileGlob]);

    expect(resultsBlob).not.toContain("resultsDir1-results1");
    expect(resultsBlob).not.toContain("resultsDir1-results2");
    expect(resultsBlob).not.toContain("resultsDir1-results1");
    expect(resultsBlob).toContain("resultsDir2-results2");
  });
});
