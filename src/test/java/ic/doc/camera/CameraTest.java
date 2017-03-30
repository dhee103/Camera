package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

    private static final byte[] IMAGE = new byte[4];

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    Sensor sensor = context.mock(Sensor.class);
    MemoryCard memoryCard = context.mock(MemoryCard.class);

    Camera camera = new Camera(sensor, memoryCard);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerUp();
        }});
        camera.powerOn();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        context.checking(new Expectations() {{
            exactly(1).of(sensor).powerDown();
        }});
        camera.powerOff();
    }

    @Test
    public void pressingShutterWhenOffDoesNothing() {
        context.checking(new Expectations() {{
            ignoring(sensor).powerUp();
            exactly(1).of(sensor).readData(); will(returnValue(IMAGE));
            exactly(1).of(memoryCard).write(IMAGE);
        }});
        camera.powerOn();
        camera.pressShutter();
    }

}
