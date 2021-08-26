package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    @DisplayName("기본형 - resolveMessageCodes 파라미터 2개만 넣어서 테스트")
    void messageCodesResolverObjectDefault() {
        String errorCode = "errorCode";
        String objectName = "objectName";

        String[] messageCodes = codesResolver.resolveMessageCodes(errorCode, objectName);

        assertThat(messageCodes).containsExactly(
                errorCode + "." + objectName,
                errorCode
        );

        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
    }

    @Test
    @DisplayName("기본형 - resolveMessageCodes 파라미터 전부 넣어서 테스트")
    void messageCodesResolverFieldDefault() {
        String errorCode = "required";
        String objectName = "item";
        String field = "itemName";

        String[] messageCodes = codesResolver.resolveMessageCodes(errorCode, objectName, field, String.class);
        assertThat(messageCodes).containsExactly(

                errorCode + "." + objectName + "." + field,
                errorCode + "." + field,
                errorCode + "." + "java.lang.String",
                errorCode
        );
    }

    @Test
    @DisplayName("item 오브젝트와 required 에러 코드를 이용해서 테스트")
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    @DisplayName("item 오브젝트 required 에러코드 itemName 필드 String.class 를 이용해서 테스트")
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }
}
