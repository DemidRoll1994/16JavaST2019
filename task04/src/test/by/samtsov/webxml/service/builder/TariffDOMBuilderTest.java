package by.samtsov.webxml.service.builder;

import by.samtsov.webxml.beans.*;
import by.samtsov.webxml.beans.enums.Operator;
import by.samtsov.webxml.service.exception.BuilderException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TariffDOMBuilderTest {

    private final String XML_PATH = "target/test-classes/resources/data/tariffs.xml";

    private List<Tariff> tariffs = new ArrayList<>();

    {
        Tariff tariff = new Tariff();
        tariff.setId("tariff1");
        tariff.setName("Comfort S");
        tariff.setCreationTariffDay(LocalDate.parse("2019-08-19"));
        tariff.setOperator(Operator.A1);
        tariff.setSmsPrice(0.25);
        tariff.setPayroll(12.99);

        VoiceParameters voiceParameters = new VoiceParameters();
        voiceParameters.setPrepayment(5);
        voiceParameters.setFavoriteNumberCount(0);
        voiceParameters.setBillingInSec(60);
        List<Parameters> parameters = new ArrayList<>();
        parameters.add(voiceParameters);
        tariff.setParameters(parameters);

        CallPrices callPrices = new CallPrices();
        callPrices.setLinear(0.75);
        callPrices.setOuter(0.75);
        callPrices.setInner(0);
        List<Prices> prices = new ArrayList<>();
        prices.add(callPrices);
        tariff.setPrices(prices);

        tariffs.add(tariff);


        Tariff tariff2 = new Tariff();
        tariff2.setId("tariff15");
        tariff2.setName("play");
        tariff2.setCreationTariffDay(LocalDate.parse("2016-01-01"));
        tariff2.setOperator(Operator.LIFE);
        tariff2.setSmsPrice(0.048);
        tariff2.setPayroll(19.90);

        InternetParameters internetParameters = new InternetParameters();
        internetParameters.setPrepayment(5);
        internetParameters.setIncludedTraffic(100);
        internetParameters.setBillingInMB(0.05);
        List<Parameters> parameters2 = new ArrayList<>();
        parameters2.add(internetParameters);
        tariff2.setParameters(parameters2);

        CallPrices callPrices2 = new CallPrices();
        callPrices2.setLinear(0.1);
        callPrices2.setOuter(0.1);
        callPrices2.setInner(0);
        List<Prices> prices2 = new ArrayList<>();
        prices2.add(callPrices2);
        tariff2.setPrices(prices2);

        tariffs.add(tariff2);
    }

    @Test
    public void testBuildTariffs() throws BuilderException {
        TariffDOMBuilder tariffDOMBuilder = new TariffDOMBuilder();
        tariffDOMBuilder.buildTariffs(XML_PATH);
        List<Tariff> tariffs1 = tariffDOMBuilder.getTariffs();
        Assert.assertTrue(tariffs1.get(0).getId().equals(tariffs.get(0).getId())
                && tariffs1.get(0).getCreationTariffDay().equals(tariffs.get(0).getCreationTariffDay())
                && tariffs1.get(0).getName().equals(tariffs.get(0).getName())
                && tariffs1.get(0).getOperator().equals(tariffs.get(0).getOperator())
                && tariffs1.get(0).getParameters().equals(tariffs.get(0).getParameters())
                && tariffs1.get(0).getPayroll() == tariffs.get(0).getPayroll()
                && tariffs1.get(0).getPrices().equals(tariffs.get(0).getPrices())
                && tariffs1.get(0).getSmsPrice() == tariffs.get(0).getSmsPrice());
    }
}