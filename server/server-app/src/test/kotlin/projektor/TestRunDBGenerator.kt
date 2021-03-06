package projektor

import java.math.BigDecimal
import java.sql.Timestamp
import java.time.LocalDateTime
import projektor.database.generated.tables.daos.*
import projektor.database.generated.tables.pojos.TestCase as TestCaseDB
import projektor.database.generated.tables.pojos.TestFailure as TestFailureDB
import projektor.database.generated.tables.pojos.TestRun as TestRunDB
import projektor.database.generated.tables.pojos.TestSuite as TestSuiteDB
import projektor.database.generated.tables.pojos.TestSuiteGroup as TestSuiteGroupDB
import projektor.incomingresults.mapper.parsePackageAndClassName
import projektor.server.api.PublicId

class TestRunDBGenerator(
    private val testRunDao: TestRunDao,
    private val testSuiteGroupDao: TestSuiteGroupDao,
    private val testSuiteDao: TestSuiteDao,
    private val testCaseDao: TestCaseDao,
    private val testFailureDao: TestFailureDao
) {
    fun createTestRun(publicId: PublicId, testSuiteDataList: List<TestSuiteData>): TestRunDB {
        val testRun = createTestRun(publicId, testSuiteDataList.size)
        testRunDao.insert(testRun)

        testSuiteDataList.forEachIndexed { testSuiteIdx, testSuiteData ->
            var testCaseIdx = 1

            val testSuite = createTestSuite(testRun.id, testSuiteData.packageAndClassName, testSuiteIdx + 1)
            testSuite.testCount = testSuiteData.passingTestCaseNames.size + testSuiteData.failingTestCaseNames.size + testSuiteData.skippedTestCaseNames.size
            testSuite.passingCount = testSuiteData.passingTestCaseNames.size
            testSuite.failureCount = testSuiteData.failingTestCaseNames.size
            testSuite.skippedCount = testSuiteData.skippedTestCaseNames.size
            testSuiteDao.insert(testSuite)

            testSuiteData.passingTestCaseNames.forEach { testCaseName ->
                val testCase = createTestCase(testSuite.id, testCaseName, testCaseIdx, true)
                testCaseDao.insert(testCase)
                testCaseIdx += 1
            }

            testSuiteData.failingTestCaseNames.forEach { testCaseName ->
                val testCase = createTestCase(testSuite.id, testCaseName, testCaseIdx, false)
                testCaseDao.insert(testCase)

                val testFailure = createTestFailure(testCase.id, testCaseName)
                testFailureDao.insert(testFailure)

                testCaseIdx += 1
            }

            testSuiteData.skippedTestCaseNames.forEach { skippedTestCaseName ->
                val testCase = createTestCase(testSuite.id, skippedTestCaseName, testCaseIdx, false)
                testCase.skipped = true
                testCaseDao.insert(testCase)

                testCaseIdx += 1
            }
        }

        return testRun
    }

    fun addTestSuiteGroupToTestRun(testSuiteGroup: TestSuiteGroupDB, testRun: TestRunDB, testSuiteClassNames: List<String>) {
        val testSuiteDBs = testSuiteDao.fetchByTestRunId(testRun.id).filter { it.className in testSuiteClassNames }

        testSuiteDBs.forEach { testSuiteDB ->
            testSuiteDB.testSuiteGroupId = testSuiteGroup.id
            testSuiteDao.update(testSuiteDB)
        }
    }
}

data class TestSuiteData(
    val packageAndClassName: String,
    val passingTestCaseNames: List<String>,
    val failingTestCaseNames: List<String>,
    val skippedTestCaseNames: List<String>
)

fun createTestRun(publicId: PublicId, totalTestCount: Int): TestRunDB = TestRunDB()
        .setPublicId(publicId.id)
        .setTotalTestCount(totalTestCount)
        .setTotalPassingCount(totalTestCount)
        .setTotalFailureCount(0)
        .setTotalSkippedCount(0)
        .setCumulativeDuration(BigDecimal("30.000"))
        .setAverageDuration(BigDecimal("30.000").divide(totalTestCount.toBigDecimal()))
        .setSlowestTestCaseDuration(BigDecimal("10.000"))
        .setPassed(true)

fun createTestSuite(testRunId: Long, packageAndClassName: String, idx: Int): TestSuiteDB = TestSuiteDB()
        .setTestRunId(testRunId)
        .setPackageName(parsePackageAndClassName(packageAndClassName).first)
        .setClassName(parsePackageAndClassName(packageAndClassName).second)
        .setIdx(idx)
        .setTestCount(6)
        .setPassingCount(3)
        .setFailureCount(2)
        .setSkippedCount(1)
        .setDuration(BigDecimal.TEN)
        .setStartTs(Timestamp.valueOf(LocalDateTime.now()))
        .setHostname("hostname")

fun createTestCase(testSuiteId: Long, name: String, idx: Int, passed: Boolean): TestCaseDB = TestCaseDB()
        .setTestSuiteId(testSuiteId)
        .setName(name)
        .setIdx(idx)
        .setClassName("${name}ClassName")
        .setDuration(BigDecimal("2.5"))
        .setPassed(passed)
        .setSkipped(false)

fun createTestFailure(testCaseId: Long, testCaseName: String): TestFailureDB = TestFailureDB()
        .setTestCaseId(testCaseId)
        .setFailureMessage("$testCaseName failure message")
        .setFailureText("$testCaseName failure text")
        .setFailureType("$testCaseName failure type")
