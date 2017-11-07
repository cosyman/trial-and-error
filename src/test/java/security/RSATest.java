package security;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.sound.midi.Soundbank;

/**
 * @author 十境
 */
public class RSATest {
    private static final ASN1ObjectIdentifier sha1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.14.3.2.26");

    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzjSV4lw/5Ok23IuFtDFh6ifF1\n" +
            "+hmnsU8PrQHbOjoTbXzDJ3xWEldUMM3NBAaUKEPnjn8j9i/JNtBaSuDkzwtC3dH3\n" +
            "cjmIBU+gFuM6XaCVwWaQuBzykmieptOWyJyRBelLlQvz4KhcXTyi8SkttsN2kYUj\n" +
            "YZFwCZQG4CZG+7rxfwIDAQAB";

    private String privateKey = "MIICXwIBAAKBgQCzjSV4lw/5Ok23IuFtDFh6ifF1+hmnsU8PrQHbOjoTbXzDJ3xW\n" +
            "EldUMM3NBAaUKEPnjn8j9i/JNtBaSuDkzwtC3dH3cjmIBU+gFuM6XaCVwWaQuBzy\n" +
            "kmieptOWyJyRBelLlQvz4KhcXTyi8SkttsN2kYUjYZFwCZQG4CZG+7rxfwIDAQAB\n" +
            "AoGBAKskqsbbFrq0vEcH1F+hGOUUfze5AKM6+uUyP0a7pYfHINjT1kwkh2EUc9Yr\n" +
            "NCochz08MiwRCVN+D/NA1ETEpTGpkIPucaEudhxL73YGCneKOpE4t3V/3oh+BHYA\n" +
            "G1ygTENGuXHkl/EGmJ+h7Ed2PYSmo5OYtaqOP9gjgy+3Po+hAkEA5d05EZGEmTuK\n" +
            "GzDD6jhBjhGg3L18JT2W0B1Mo0p/HgqpDCP4nqUGMn+WBYtI7LiLFMGPuL8gNxSJ\n" +
            "FTyKPJcuNQJBAMf3cTkLvwhiAQZ7+5VrdBCBdf+yFmtAAusy2vhiKg2Z76YU6RY0\n" +
            "faEiTBHggbg8i1ADBcg5Y+MLZAKRZt+SJ2MCQQDLT0YgweLUXZMjxZgjXCqva+wq\n" +
            "EfbyQW1ZGfElJMkWWzt2H4LRXwkCNVMOCEoyBnANpb5nG7V0wlcVT3y9ZsJZAkEA\n" +
            "u95Z3YKPo8yPsgvhvewKS0eWGPiz32dwVpCc8Sco40ZwxNR4eIgwT6btWQ5YUUNw\n" +
            "j2eHkyl4EuOsvkzlHb8uiQJBAMvN40FVqIeOd7aiaGhtqiu4nnhSnS2op7abrsgF\n" +
            "MkgL+w2ityiWVsMwbNLsCsj5wfXWVBPkp8JyGyeF+W3QCWg=";

    private String sign = "M4KUkFfE9ASDX9kpqelNO9VAVRILudUJJl1cRad+Q/H8W71a/+YCkBKI0/O3Se0pC5XlT7ZUHPAe\nuTssTBb9XxfPxlfRUJ3HOgGzF7jg1Vin5kW+5aBbB7rpa2yPVqdEU55qDdN+jT3XQeAZR8byZEX7\nuWwp5c8piPf/NM85Vso=";

    private String content = "aGVsbG8=";

    private String privateKey2 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALkvslJ0q07LizwAv0eocFsSsD1t\n" +
            "DWHkqlkA4TKB4VNDxHuQvKS805TLl49uMjbwNd5T3yVCBkX7B8iyUbG12c+ijCW7qA1MKptxXz3R\n" +
            "QEsJNhGy9ITiShkpid21vnc3hg1fBjyRgVj28Cq7fiVEJDuMQusMkOc80dRHV93YsmqfAgMBAAEC\n" +
            "gYAXK6icOIzWpxEcyNsmFCU6v/PdO6/Y98t4p2uF7kpLKMeEzm9MDXRLwc2M3LTbuuKCluvstX4j\n" +
            "c21d5zHhkmdWJNjdtD8jpW5BM7sH9yivETQTG5p32WREJOAt/iBcdQxt8q1rgAS34QoGWFhzG7oL\n" +
            "93qMX1nvefWvPQ+KYGNNgQJBAOm6boAKwty6dZ/mvsuXqmat6Jj+8VCeC2d96+tuY3L242Pv2DiD\n" +
            "80tzbIPuG8vuJADDwdbiXSkDlxUkt25WFicCQQDK1SH4B3DBTHmsn9ed/VmlLl0Pi5JzEsfKZ0QD\n" +
            "Tv9eYZ1lYOBOfkZFRxCQA5D07awaJ2BYs1JCmUDbUqMLxorJAkBIkIwcDrs/Aljggyg7V2QdKuMr\n" +
            "FQPgzqClye+EEaqol8QzU9Sifzz68OQA9d0FbHMbFavJxk+8GxQ+CT81715DAkBMK1u1S/4A6Ros\n" +
            "RF5xWynmCRIze0OE6UGT9dGpuGnX5Kp53rZ9Y5g5/eTvYBd4kyR1MeJRjQ3QKsMbWd4W1vlZAkEA\n" +
            "lhgsYwU4+fiY/quzL88qSTL0TeYEyQThpn6WvGFQcevIJj88L8S0Uc7UjKaDNpbUYPsS4v0R1szQ\n" +
            "llVG/z6Pqg==";

    private String publicKey2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5L7JSdKtOy4s8AL9HqHBbErA9bQ1h5KpZAOEy\n" +
            "geFTQ8R7kLykvNOUy5ePbjI28DXeU98lQgZF+wfIslGxtdnPoowlu6gNTCqbcV890UBLCTYRsvSE\n" +
            "4koZKYndtb53N4YNXwY8kYFY9vAqu34lRCQ7jELrDJDnPNHUR1fd2LJqnwIDAQAB";


    @Test
    public void verify() {
        String sign2 = H5RsaUtil.sign(content, privateKey);

        boolean result = H5RsaUtil.doCheck(content,
                sign2, publicKey);
        System.out.println(result);
    }


    private String signBase = "KPm1BHoHZ37SmOOOWstYy74w58JomXTWVfRY+TZEEJbYdGhvmpEqKBLtM1SqYrPbkp2ODmP9svT2\nxZq9uPOcJbRYlEF+55ybUprNeOibQfPUj5tp3SkKpBpV0hDgyheDYI3sn5/oSyHbHLCk70ZObdxB\nzrMO97mdVv1kyGX+tPo=";

    @Test
    public void verifyBase() {
        String sign2 = H5RsaUtil.sign(content, privateKey);

        boolean result = H5RsaUtil.doCheck(content,
                signBase, publicKey);
        System.out.println(result);
    }

    @Test
    public void verify2() {
        String shaBase64 = "L0pPBb8XB029pFCHpv6B3yVQhQA=";
        String sign2 = H5RsaUtil.sign(shaBase64, privateKey2);
        System.out.println(sign2);
//        sign2 = "suGv+nmRxaQlcaV0bS1Q/GplZJNR+28WeNkwCVIBpnfiVCN0t/AvzEJ5t4icmW/SgV2fYDgc+8Op\n" +
//                "LkDWjQ12O+RrWuSWK3VOUHQqEtfMaKmzCxpGcOzpxlR+U6uGUhwGSc9jEng5tiTFRcAXAbjZolkZ\n" +
//                "ZXxgTP2pAd9mjDDSqGY=";
        boolean result = H5RsaUtil.doCheck(shaBase64,
                sign2, publicKey2);
        System.out.println(result);
    }

    @Test
    public void generate() {
        H5RsaUtil.genKeyPair("/Users/datian/kp");
    }
}
