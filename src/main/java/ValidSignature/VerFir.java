package ValidSignature;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class VerFir {

    public boolean VerFir(String rutaClave, String rutaFirma, String rutaDoc) {
        if (rutaClave == null || rutaFirma == null || rutaDoc == null) {
            System.err.println("Las rutas no pueden ser nulas.");
            return false;
        }

        try {
            PublicKey pubKey = cargarClavePublica(rutaClave);
            byte[] firma = cargarFirmaDigital(rutaFirma);
            Signature sig = inicializarVerificacion(pubKey);
            actualizarFirma(sig, rutaDoc);

            return sig.verify(firma);

        } catch (Exception e) {
            System.err.println("Error durante la verificación: " + e.getMessage());
            return false;
        }
    }

    private PublicKey cargarClavePublica(String rutaClave) throws Exception {
        FileInputStream keyfis = new FileInputStream(rutaClave);
        byte[] encKey = keyfis.readAllBytes();
        keyfis.close();

        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        return keyFactory.generatePublic(pubKeySpec);
    }

    private byte[] cargarFirmaDigital(String rutaFirma) throws IOException {
        FileInputStream sigfis = new FileInputStream(rutaFirma);
        byte[] sigToVerify = sigfis.readAllBytes();
        sigfis.close();
        return sigToVerify;
    }

    private Signature inicializarVerificacion(PublicKey pubKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pubKey);
        return sig;
    }

    private void actualizarFirma(Signature sig, String rutaDoc) throws IOException, SignatureException {
        FileInputStream datafis = new FileInputStream(rutaDoc);
        BufferedInputStream bufin = new BufferedInputStream(datafis);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufin.read(buffer)) != -1) {
            sig.update(buffer, 0, len);
        }
        bufin.close();
    }
}
