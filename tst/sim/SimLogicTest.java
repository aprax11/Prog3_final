package sim;
import automat.*;
import automat.Container;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.*;

import static org.mockito.Mockito.*;

public class SimLogicTest {
    private GeschäftslogikImpl mockGL;
    private  SimLogic sIM;
    private GanzerKuchen OBSTTORTE;
    private GanzerKuchen KREMKUCHEN;
    private GanzerKuchen OBSTKUCHEN;

    @BeforeEach
    public void start() {
        this.mockGL = mock(GeschäftslogikImpl.class);
        this.sIM = new SimLogic(mockGL);
        this.OBSTTORTE = mock(GanzerKuchen.class);
        this.KREMKUCHEN = mock(GanzerKuchen.class);
        this.OBSTKUCHEN = mock(GanzerKuchen.class);
    }

    @Test
    public void addRandomKuchenTest() {
        this.sIM.addRandomKuchen();
        verify(this.mockGL).addKuchen(anyList(), any(Container.class));
    }

    @Test
    public void removeRandomKuchenTest() {
        GanzerKuchen[] list = {KREMKUCHEN, OBSTKUCHEN, OBSTTORTE};

        when(this.mockGL.listKuchen(null)).thenReturn(list);
        this.sIM.removeRandomKuchen();

        verify(this.mockGL).löscheKuchen(anyInt());
    }

    @Test
    public void updateRandomKuchenTest() {
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        GanzerKuchen[] list = {KREMKUCHEN, OBSTKUCHEN, OBSTTORTE};
        when(this.mockGL.listKuchen(null)).thenReturn(list);
        this.sIM.updateRandomKuchen();

        verify(this.mockGL).setInspektionsdatum(anyInt());
    }

    @Test
    public void addRandomSim2KuchenTest() {
        this.sIM.einfügenSim2();
        verify(this.mockGL).addKuchen(anyList(), any(Container.class));
    }
    @Test
    public void löscheÄltestenTest() {

        GanzerKuchen[] list = {KREMKUCHEN, OBSTKUCHEN, OBSTTORTE};
        when(this.mockGL.listKuchen(null)).thenReturn(list);
        Date d = new Date(2, 1, 1, 1, 1);

        when(KREMKUCHEN.getInspektionsdatum()).thenReturn(new Date());
        when(OBSTKUCHEN.getInspektionsdatum()).thenReturn(new Date());
        when(OBSTTORTE.getInspektionsdatum()).thenReturn(d);
        when(mockGL.isFull()).thenReturn(true);
        when(mockGL.getListGröße()).thenReturn(3);
        when(mockGL.getFachnummer()).thenReturn(3);
        when(KREMKUCHEN.getFachnummer()).thenReturn(0);
        when(OBSTKUCHEN.getFachnummer()).thenReturn(1);
        when(OBSTTORTE.getFachnummer()).thenReturn(2);
        this.sIM.löscheÄltesten();

        verify(this.mockGL).löscheKuchen(2);
    }

    @Test
    public void deleteSim3Test(){
        GanzerKuchen[] list = {KREMKUCHEN, OBSTKUCHEN, OBSTTORTE};
        when(this.mockGL.listKuchen(null)).thenReturn(list);
        Date d = new Date(2, 1, 1, 1, 1);

        when(KREMKUCHEN.getInspektionsdatum()).thenReturn(new Date());
        when(OBSTKUCHEN.getInspektionsdatum()).thenReturn(d);
        when(OBSTTORTE.getInspektionsdatum()).thenReturn(d);
        when(mockGL.isFull()).thenReturn(true);
        when(mockGL.getListGröße()).thenReturn(3);
        when(mockGL.getFachnummer()).thenReturn(3);
        when(KREMKUCHEN.getFachnummer()).thenReturn(0);
        when(OBSTKUCHEN.getFachnummer()).thenReturn(1);
        when(OBSTTORTE.getFachnummer()).thenReturn(2);

        this.sIM.deleteSim3(new Random(3));

        verify(mockGL, times(2)).löscheKuchen(anyInt());
    }
}
