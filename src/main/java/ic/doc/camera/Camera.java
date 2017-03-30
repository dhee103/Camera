package ic.doc.camera;

public class Camera implements WriteListener{

    private final Sensor sensor;
    private MemoryCard memoryCard;
    private boolean powerIsOn = false;

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        if(powerIsOn) {
            memoryCard.write(sensor.readData());
        }
    }

    public void powerOn() {
        sensor.powerUp();
        powerIsOn = true;
    }

    public void powerOff() {
        sensor.powerDown();
        powerIsOn = false;
    }

    @Override
    public void writeComplete() {

    }
}

