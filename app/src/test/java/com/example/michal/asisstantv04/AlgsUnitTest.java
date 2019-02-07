package com.example.michal.asisstantv04;

import com.example.michal.asisstantv04.AlgorithmStrategies.AlgorithmContext;
import com.example.michal.asisstantv04.AlgorithmStrategies.IAlgorithm;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;
import com.example.michal.asisstantv04.RequestBuilders.RequestBuilder;
import com.example.michal.asisstantv04.RequestBuilders.RequestDirector;

import org.junit.Test;

import static org.junit.Assert.*;


public class AlgsUnitTest {


    IAlgorithm algorithm = AlgorithmContext.getInstance().getIAlgorithm();

    public AlgsUnitTest(){
        AlgorithmContext.getInstance().setIAlgorithm(AlgorithmContext.MONGE_ELKAN_ALG);
    }


    @Test
    public void actionTest1() {
//        System.out.println(AlgorithmContext.getInstance().getIAlgorithm().getClass().toString());
        Request request = new Request(0, "Zadzwoń do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest2() {
        Request request = new Request(0, "dzwoń do jan kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest3() {
        Request request = new Request(0, "dzwonienie do jan kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest4() {
        Request request = new Request(0, "Zadzwonić do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest5() {
        Request request = new Request(0, "Chcę zadzwonić do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest6() {
        Request request = new Request(0, "Chciałbym zadzwonić do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest7() {
        Request request = new Request(0, "Chcę dzwonić do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest8() {
        Request request = new Request(0, "Oddzwoń do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest9() {
        Request request = new Request(0, "przedzwoń do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest10() {
        Request request = new Request(0, "zatelefonuj do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest11() {
//        System.out.println(AlgorithmContext.getInstance().getIAlgorithm().getClass().toString());
        Request request = new Request(0, "Zadzwoń do wasilij", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest12() {
        Request request = new Request(0, "dzwoń do test", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest13() {
        Request request = new Request(0, "dzwonienie do piotr nowak", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest14() {
        Request request = new Request(0, "niech zadzwoni do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest15() {
        Request request = new Request(0, "Chcę zadzwonić do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest16() {
        Request request = new Request(0, "Proszę zadzwonić do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest17() {
        Request request = new Request(0, "Proszę dzwonić do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest18() {
        Request request = new Request(0, "zadzwonię do Piotr nowak", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest19() {
        Request request = new Request(0, "zdzwoń Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest20() {
        Request request = new Request(0, "zatelefonować do Jan Kowalski", ActionSchema.TYPE_CALLING);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    //////////////////////////////////////////////////////////////////
    @Test
    public void actionTest21() {
        Request request = new Request(0, "wyślij wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest22() {
        Request request = new Request(0, "wyślij SMS do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest23() {
        Request request = new Request(0, "wyślij eSeMeSa do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest24() {
        Request request = new Request(0, "wysyłanie wiadomości do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest25() {
        Request request = new Request(0, "wysyłanie esemesa do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest26() {
        Request request = new Request(0, "wysłanie esemesa do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest27() {
        Request request = new Request(0, "wyślijmy wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest28() {
        Request request = new Request(0, "proszę wyślij wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest29() {
        Request request = new Request(0, "niech wyśle wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest30() {
        Request request = new Request(0, "wysyłaj wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest31() {
        Request request = new Request(0, "napisz wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest32() {
        Request request = new Request(0, "napisz SMS do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest33() {
        Request request = new Request(0, "napisz eSeMeSa do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest34() {
        Request request = new Request(0, "pisz wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest35() {
        Request request = new Request(0, "pisanie esemesa do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest36() {
        Request request = new Request(0, "napisz esemesa do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest37() {
        Request request = new Request(0, "napiszmy wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest38() {
        Request request = new Request(0, "proszę napisz wiadomość do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest39() {
        Request request = new Request(0, "niech napisze sms do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }
    @Test
    public void actionTest40() {
        Request request = new Request(0, "napisz sms do Jan Kowalski", ActionSchema.TYPE_SMS);
        String builder  = new TestDirector().createBuilder(request.text).action;
        assertEquals(request.action, builder);
    }

}
