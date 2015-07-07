/*
 * Created by Daniel Marell 2011-08-31 23:02
 */
package se.marell.libusb;

import java.util.Collections;
import java.util.List;

public class ListUsbDevices {

    public static void main(String[] args) {
        UsbSystem us = new LibUsbSystem(false, 0);
        try {
            us.visitUsbDevices(new UsbSystem.UsbDeviceVisitor() {
                @Override
                public List<UsbDevice> visitDevices(List<UsbDevice> allDevices) {
                    System.out.printf("%4s %4s %20s %20s %10s %4s\n",
                            "Bus",
                            "Address",
                            "Product",
                            "Manifacturer",
                            "Serial",
                            "Max packet size");
                    for (UsbDevice d : allDevices) {
                        try {
                            d.open();
                            System.out.printf("%04x %04x %20s %20s %10s %4s\n",
                                    d.get_bus_number(),
                                    d.get_address(),
                                    d.getProduct(),
                                    d.getManufacturer(),
                                    d.getSerialNumber(),
                                    getPacketSize(d));
                            d.close();
                        } catch (LibUsbNoDeviceException e) {
                            limitedDevicePrint(d, e);
                        } catch (LibUsbPermissionException e) {
                            limitedDevicePrint(d, e);
                        } catch (LibUsbOtherException e) {
                            limitedDevicePrint(d, e);
                        }
                    }
                    return Collections.emptyList();
                }
            });
        } catch (LibUsbNoDeviceException e) {
            System.out.println(e.getClass().getSimpleName() + ":" + e.getMessage());
        } catch (LibUsbPermissionException e) {
            System.out.println(e.getClass().getSimpleName() + ":" + e.getMessage());
        } catch (LibUsbOtherException e) {
            System.out.println(e.getClass().getSimpleName() + ":" + e.getMessage());
        }
        us.cleanup();
    }

    private static void limitedDevicePrint(UsbDevice d, LibUsbException e) {
        System.out.printf("%04x %04x %s\n",
                d.get_bus_number(),
                d.get_address(),
                e.getClass().getSimpleName() + ":" + e.getMessage());
    }

    private static String getPacketSize(UsbDevice d) {
        try {
            return "" + d.get_max_iso_packet_size(0);
        } catch (LibUsbNotFoundException e) {
            return e.getMessage();
        } catch (LibUsbOtherException e) {
            return e.getMessage();
        }
    }
}
