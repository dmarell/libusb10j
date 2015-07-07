/*
 * Created by Daniel Marell 2011-08-28 15:19
 */
package se.marell.libusb.jna;

import com.sun.jna.Structure;

/**
 * A structure representing the standard USB device descriptor.
 * 
 * This descriptor is documented in section 9.6.1 of the USB 2.0 specification.
 * All multiple-byte fields are represented in host-endian format.
 */
public class libusb_device_descriptor extends Structure {
    /**
     * Size of this descriptor (in bytes).
     */
    public byte bLength;

    /**
     * Descriptor type.
     * Will have value libusb_descriptor_type::LIBUSB_DT_DEVICE LIBUSB_DT_DEVICE in this context.
     */
    public byte bDescriptorType;

    /**
     * USB specification release number in binary-coded decimal.
     * A value of 0x0200 indicates USB 2.0, 0x0110 indicates USB 1.1, etc.
     */
    public short bcdUSB;

    /**
     * USB-IF class code for the device.
     */
    public byte bDeviceClass;

    /**
     * USB-IF subclass code for the device, qualified by the bDeviceClass value.
     */
    public byte bDeviceSubClass;

    /**
     * USB-IF protocol code for the device, qualified by the bDeviceClass and bDeviceSubClass values.
     */
    public byte bDeviceProtocol;

    /**
     * Maximum packet size for endpoint 0.
     */
    public byte bMaxPacketSize0;

    /**
     * USB-IF vendor ID.
     */
    public short idVendor;

    /**
     * USB-IF product ID.
     */
    public short idProduct;

    /**
     * Device release number in binary-coded decimal.
     */
    public short bcdDevice;

    /**
     * Index of string descriptor describing manufacturer.
     */
    public byte iManufacturer;

    /**
     * Index of string descriptor describing product.
     */
    public byte iProduct;

    /**
     * Index of string descriptor containing device serial number.
     */
    public byte iSerialNumber;

    /**
     * Number of possible configurations.
     */
    public byte bNumConfigurations;

    @Override
    public String toString() {
        return "libusb_device_descriptor {" +
                " bLength=" + bLength +
                " bDescriptorType=" + bDescriptorType +
                " bcdUSB=" + bcdUSB +
                " bDeviceClass=" + bDeviceClass +
                " bDeviceSubClass=" + bDeviceSubClass +
                " bDeviceProtocol=" + bDeviceProtocol +
                " bMaxPacketSize0=" + bMaxPacketSize0 +
                " idVendor=" + idVendor +
                " idProduct=" + idProduct +
                " bcdDevice=" + bcdDevice +
                " iManufacturer=" + iManufacturer +
                " iProduct=" + iProduct +
                " iSerialNumber=" + iSerialNumber +
                " bNumConfigurations=" + bNumConfigurations +
                "}";
    }
}
