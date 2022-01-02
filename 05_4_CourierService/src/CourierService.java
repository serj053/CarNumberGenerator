public class CourierService {

    private final Dimensions dimensions;
    private final double weight;
    private final String deliveryAddress;//адресс доставки
    private final boolean isFlip;// можно ли переворачивать;
    private final String registrationNumber;//регистрационный номер
    private final boolean isFragile;//является ли груз хрупким

    public CourierService(Dimensions dimension, double weight, String deliveryAddress,
                          boolean isFlip, String registrationNumber, boolean isFragile) {
        this.dimensions = new Dimensions(dimension.getHeight(), dimension.getWidth(), dimension.getLength());
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.isFlip = isFlip;
        this.registrationNumber = registrationNumber;
        this.isFragile = isFragile;
    }

    public CourierService newDeliveryAddress(String deliveryAddress) {
        return new CourierService(
                dimensions,
                weight,
                deliveryAddress,
                isFlip,
                registrationNumber,
                isFragile);
    }

    public CourierService newDimension(Dimensions dimensions) {
        return new CourierService(
                dimensions,
                weight,
                deliveryAddress,
                isFlip,
                registrationNumber,
                isFragile);
    }

    public CourierService newWeight(double weight) {
        return new CourierService(
                dimensions,
                weight,
                deliveryAddress,
                isFlip,
                registrationNumber,
                isFragile);
    }

    public Dimensions getDimension() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isFlip() {
        return isFlip;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragile() {
        return isFragile;
    }
}
