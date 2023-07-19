package org.bmserras.sot.isystem0;

import jakarta.xml.bind.JAXBException;
import mdeos.isos.isystem0lib.ISystem0ZK;
import mdeos.isos.model.Service;
import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class ISystem0Test {

    @Test
    public void iSystem0Test() throws UnsupportedEncodingException, InterruptedException, KeeperException, JAXBException {

        ISystem0ZK iSystem0ZK = new ISystem0ZK();

        Service service = iSystem0ZK.getService("/TFM/SIGET/Radar A1 12 C/Cabin");
        System.out.println(service);
    }

}
