package security;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.pkcs.PKCS12PfxPdu;
import org.bouncycastle.pkcs.PKCS12SafeBag;
import org.bouncycastle.pkcs.PKCS12SafeBagFactory;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.io.Streams;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * @author 十境
 */
public class P12Test {

    private String iosCertificate = "Certificates.p12";
    private String androidCertificate = "testmpaas.jks";

    @Test
    public void parseIOS(){
        InputStream contentInput = P12Test.class.getResourceAsStream("subway.p12");

        try {
            KeyStore keyStore = KeyStore.getInstance("pkcs12");
            keyStore.load(contentInput, "1".toCharArray());
            String firstAlias = keyStore.aliases().nextElement();

            X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(firstAlias);
            System.out.println(x509Certificate.getNotAfter());
            System.out.println(x509Certificate.getSubjectDN().getName());

             LdapName ldapDN = new LdapName(x509Certificate.getSubjectDN().getName());
            for(Rdn rdn: ldapDN.getRdns()) {
                System.out.println(rdn.getType() + " -> " + rdn.getValue());
            }

            final Map<String, String> splitKeyValues = Splitter.on(", ").trimResults()
                    .withKeyValueSeparator("=")
                    .split(x509Certificate.getSubjectDN().getName());
            System.out.println(splitKeyValues.get("CN"));;
//            String cn = takeCommonNameFromDN(x509Certificate.getSubjectDN().getName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void base64Test() throws IOException {
        InputStream contentInput = P12Test.class.getResourceAsStream("subway.p12");
        String contentBase = Base64.getEncoder().encodeToString(IOUtils.toByteArray(contentInput));

        System.out.println(contentBase);

        System.out.println();

        contentInput = P12Test.class.getResourceAsStream("TestAppInstall2.mobileprovision");
        contentBase = Base64.getEncoder().encodeToString(IOUtils.toByteArray(contentInput));

        System.out.println(contentBase);
    }

    @Test
    public void testRead() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream contentInput = P12Test.class.getResourceAsStream(iosCertificate);
        String contentBase = Base64.getEncoder().encodeToString(IOUtils.toByteArray(contentInput));
        System.out.println(contentBase);
        byte[] input = new BASE64Decoder().decodeBuffer(contentBase);
        keyStore.load(new ByteArrayInputStream(input), "12345678".toCharArray());
        Certificate targetCertificate = keyStore.getCertificate("mpaas");

        Enumeration<String> aliases = keyStore.aliases();
//        System.out.println(aliases.hasMoreElements());
        while (aliases.hasMoreElements()) {

            String alias = aliases.nextElement();
            System.out.println(alias);
            X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);
            System.out.println(certificate.getNotAfter().toString());
            System.out.println(certificate.getIssuerDN());
            System.out.println(certificate.getSubjectAlternativeNames());
            System.out.println(certificate.getSigAlgName());
        }

    }

    @Test
    public void testProvision() throws Exception {
        byte[] input = IOUtils.toByteArray(P12Test.class.getResourceAsStream("TestAppInstall.mobileprovision"));
        CMSSignedData signature = new CMSSignedData(input);
        String contentBase = Base64.getEncoder().encodeToString(input);
        System.out.println(contentBase);
        Store cs = signature.getCertificates();
        //the following array will contain the content of xml document

        CMSProcessable sc = signature.getSignedContent();
        byte[] data = (byte[]) sc.getContent();

        NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(data);

        System.out.println(rootDict.get("UUID").toString());
        System.out.println(rootDict.get("ExpirationDate").toString());
        System.out.println((Date) rootDict.get("ExpirationDate").toJavaObject());

    }


    private static PKCS12PfxPdu readPKCS12File(InputStream pfxIn)
            throws Exception {
        PKCS12PfxPdu pfx = new PKCS12PfxPdu(Streams.readAll(pfxIn));


        ContentInfo[] infos = pfx.getContentInfos();

        Map certMap = new HashMap();
        Map certKeyIds = new HashMap();
        Map privKeyMap = new HashMap();
        Map privKeyIds = new HashMap();

        InputDecryptorProvider inputDecryptorProvider = null;
//                new JcePKCSPBEInputDecryptorProviderBuilder()
//                .setProvider("BC").build();
        JcaX509CertificateConverter jcaConverter = new JcaX509CertificateConverter().setProvider("BC");

        for (int i = 0; i != infos.length; i++) {
            if (infos[i].getContentType().equals(PKCSObjectIdentifiers.encryptedData)) {
                PKCS12SafeBagFactory dataFact = new PKCS12SafeBagFactory(infos[i], inputDecryptorProvider);

                PKCS12SafeBag[] bags = dataFact.getSafeBags();

                for (int b = 0; b != bags.length; b++) {
                    PKCS12SafeBag bag = bags[b];

                    X509CertificateHolder certHldr = (X509CertificateHolder) bag.getBagValue();
                    X509Certificate cert = jcaConverter.getCertificate(certHldr);

                    Attribute[] attributes = bag.getAttributes();
                    for (int a = 0; a != attributes.length; a++) {
                        Attribute attr = attributes[a];

                        if (attr.getAttrType().equals(PKCS12SafeBag.friendlyNameAttribute)) {
                            certMap.put(((DERBMPString) attr.getAttributeValues()[0]).getString(), cert);
                        } else if (attr.getAttrType().equals(PKCS12SafeBag.localKeyIdAttribute)) {
                            certKeyIds.put(attr.getAttributeValues()[0], cert);
                        }
                    }
                }
            } else {
                PKCS12SafeBagFactory dataFact = new PKCS12SafeBagFactory(infos[i]);

                PKCS12SafeBag[] bags = dataFact.getSafeBags();

                PKCS8EncryptedPrivateKeyInfo encInfo = (PKCS8EncryptedPrivateKeyInfo) bags[0].getBagValue();
                PrivateKeyInfo info = encInfo.decryptPrivateKeyInfo(inputDecryptorProvider);

                KeyFactory keyFact = KeyFactory.getInstance(info.getPrivateKeyAlgorithm().getAlgorithm().getId(), "BC");
                PrivateKey privKey = keyFact.generatePrivate(new PKCS8EncodedKeySpec(info.getEncoded()));

                Attribute[] attributes = bags[0].getAttributes();
                for (int a = 0; a != attributes.length; a++) {
                    Attribute attr = attributes[a];

                    if (attr.getAttrType().equals(PKCS12SafeBag.friendlyNameAttribute)) {
                        privKeyMap.put(((DERBMPString) attr.getAttributeValues()[0]).getString(), privKey);
                    } else if (attr.getAttrType().equals(PKCS12SafeBag.localKeyIdAttribute)) {
                        privKeyIds.put(privKey, attr.getAttributeValues()[0]);
                    }
                }
            }
        }

        System.out.println("########## PFX Dump");
        for (Iterator it = privKeyMap.keySet().iterator(); it.hasNext(); ) {
            String alias = (String) it.next();

            System.out.println("Key Entry: " + alias + ", Subject: " + (((X509Certificate) certKeyIds.get(privKeyIds.get(privKeyMap.get(alias)))).getSubjectDN()));
        }

        for (Iterator it = certMap.keySet().iterator(); it.hasNext(); ) {
            String alias = (String) it.next();

            System.out.println("Certificate Entry: " + alias + ", Subject: " + (((X509Certificate) certMap.get(alias)).getSubjectDN()));
        }
        System.out.println();

        return pfx;
    }

}
