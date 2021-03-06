/*
 * This file is generated by jOOQ.
 */
package projektor.database.generated;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.test_case_id_seq</code>
     */
    public static final Sequence<Long> TEST_CASE_ID_SEQ = new SequenceImpl<Long>("test_case_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.test_case_test_suite_id_seq</code>
     */
    public static final Sequence<Long> TEST_CASE_TEST_SUITE_ID_SEQ = new SequenceImpl<Long>("test_case_test_suite_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.test_failure_id_seq</code>
     */
    public static final Sequence<Long> TEST_FAILURE_ID_SEQ = new SequenceImpl<Long>("test_failure_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.test_failure_test_case_id_seq</code>
     */
    public static final Sequence<Long> TEST_FAILURE_TEST_CASE_ID_SEQ = new SequenceImpl<Long>("test_failure_test_case_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.test_run_id_seq</code>
     */
    public static final Sequence<Long> TEST_RUN_ID_SEQ = new SequenceImpl<Long>("test_run_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.test_suite_group_id_seq</code>
     */
    public static final Sequence<Long> TEST_SUITE_GROUP_ID_SEQ = new SequenceImpl<Long>("test_suite_group_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.test_suite_id_seq</code>
     */
    public static final Sequence<Long> TEST_SUITE_ID_SEQ = new SequenceImpl<Long>("test_suite_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.test_suite_test_run_id_seq</code>
     */
    public static final Sequence<Long> TEST_SUITE_TEST_RUN_ID_SEQ = new SequenceImpl<Long>("test_suite_test_run_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));
}
