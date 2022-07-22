package util;

import org.assertj.core.api.Assertions;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Used SHA-3-512 (hash encrypt)
 * */
public class EncryptTest {

    private static final String TEST_STRING_TO_DECRYPT = "S89ima93";
    private static final String TEST_LIKE_STRING_TO_DECRYPT = "S89ima93";
    private static final String TEST_UNLIKE_STRING_TO_DECRYPT = "S89ima95";
    private static final String TEST_BIGGER_STRING_TO_DECRYPT = "@S89ima95abc89!!lkm00";

    @BeforeClass
    public static void globalSetUp() throws Exception {
        digestSHA3 = new SHA3.Digest512();
    }

    private static SHA3.DigestSHA3 digestSHA3;

    @Test
    public void encryptTest() {
        byte[] digest1 = digestSHA3.digest(TEST_STRING_TO_DECRYPT.getBytes());
        byte[] digest2 = digestSHA3.digest(TEST_LIKE_STRING_TO_DECRYPT.getBytes());
        byte[] digest3 = digestSHA3.digest(TEST_UNLIKE_STRING_TO_DECRYPT.getBytes());

        String inHex = Hex.toHexString(digest1);
        String inHexSame = Hex.toHexString(digest1);
        String inHexLike = Hex.toHexString(digest2);
        String inHexUnlike = Hex.toHexString(digest3);

        Assertions.assertThat(inHex).isEqualTo(inHex);
        Assertions.assertThat(inHex).isEqualTo(inHexSame);
        Assertions.assertThat(inHex).isEqualTo(inHexLike);
        Assertions.assertThat(inHex).isNotEqualTo(inHexUnlike);

        Assertions.assertThat(inHex.length()).isEqualTo(128);

        byte[] digest4 = digestSHA3.digest(TEST_BIGGER_STRING_TO_DECRYPT.getBytes());
        String inHexBiggest = Hex.toHexString(digest4);
        Assertions.assertThat(inHexBiggest.length()).isEqualTo(128);

    }

}
