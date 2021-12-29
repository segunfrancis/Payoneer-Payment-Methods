package com.segunfrancis.payoneerpaymentmethods.repository;

import com.segunfrancis.payoneerpaymentmethods.data.remote.model.BaseResponse;
import com.segunfrancis.payoneerpaymentmethods.data.repository.IPaymentMethodRepository;
import com.segunfrancis.payoneerpaymentmethods.data.repository.PaymentMethodRepository;
import com.segunfrancis.payoneerpaymentmethods.util.FakePaymentMethodApi;
import com.segunfrancis.payoneerpaymentmethods.util.MockResponseFileReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;

@RunWith(JUnit4.class)
public class PaymentMethodRepositoryTest {

    private IPaymentMethodRepository repository;
    private MockWebServer mockWebServer;

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        repository = new PaymentMethodRepository(FakePaymentMethodApi.provideFakeApi());
    }

    @Test
    public void readSampleJsonResponse() {
        MockResponseFileReader reader = new MockResponseFileReader("success_response.json");
        Assert.assertNotNull(reader.content);
    }

    @Test
    public void checkResponseAndResponseCode() throws IOException {
        MockResponse response = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(new MockResponseFileReader("success_response.json").content);
        mockWebServer.enqueue(response);

        Response<BaseResponse> actualResponse = repository.getPaymentMethods();
        Assert.assertEquals(response.toString().contains("200"), actualResponse.code() == 200);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }
}
