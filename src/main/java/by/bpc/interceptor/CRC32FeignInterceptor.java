package by.bpc.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.zip.CRC32;

@Component
public class CRC32FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate.method().equals("POST") && requestTemplate.body() != null) {
            CRC32 crc32 = new CRC32();
            crc32.update(requestTemplate.body());
            requestTemplate.header("CRC32", Long.toHexString(crc32.getValue()));
        }
    }
}
