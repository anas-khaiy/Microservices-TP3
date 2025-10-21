package com.example.demo;

import metier.IMetier;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.Presentation2;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OcpSelectionTest {

    @Test
    public void devProfile_choisitDao2_300() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");     // DaoImpl2 (150)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        Assertions.assertEquals(300.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void prodProfile_choisitDao_200() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");    // DaoImpl (100)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        Assertions.assertEquals(200.0, m.calcul(), 1e-6);
        ctx.close();
    }
}