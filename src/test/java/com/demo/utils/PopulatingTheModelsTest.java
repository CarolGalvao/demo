package com.demo.utils;

import com.demo.model.Vertex;
import junitparams.JUnitParamsRunner;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PopulatingTheModelsTest {

   PopulatingTheModels populating = new PopulatingTheModels();
   Vertex vertex = new Vertex();

    @Test
    public void isValidEntryOk(){
        Assert.assertTrue(populating.isValidEntry("GRU","ORL","10"));
    }

    @Test
    public void isValidEntryEmptyString(){
        Assert.assertFalse(populating.isValidEntry("","ORL","10"));
    }

    @Test
    public void isValidEntryNullString(){
        Assert.assertFalse(populating.isValidEntry(null,"ORL","10"));
    }

    @Test
    public void addVertexNewCity(){
        vertex.setCity("GRU");
        Assert.assertEquals(vertex.getCity(), populating.addVertex("GRU").getCity());
    }

    @Test
    public void findCityNotFound(){
        populating.findCity("XXX");
        Assert.assertNull(populating.findCity("XXX"));
    }

    @Test
    public void findCityFound(){
        populating.addGraphEntry("XXX", "YYY","10");
        populating.findCity("XXX");
        Assert.assertEquals("XXX", populating.findCity("XXX").getCity());
    }

    @Test
    public void addGraphEntryOk(){
        populating.addGraphEntry("SJC", "SRP", "80");
        MatcherAssert.assertThat("Must be complited",
                (populating.graph.getVertexes() != null &&  populating.graph.getLines() != null));
    }

    @Test
    public void addGraphEntryNull(){
        populating.addGraphEntry(null, null, "80");
        MatcherAssert.assertThat("Must be complited",
                (populating.graph.getVertexes() == null &&  populating.graph.getLines() == null));
    }

    @Test
    public void findCityWithGraph(){
        populating.addGraphEntry("SJC", "SRP", "80");
        Assert.assertEquals("SJC", populating.findCity(populating.graph, "SJC").getCity());
    }

}
