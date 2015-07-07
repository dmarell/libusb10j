/*
 * Created by Daniel Marell 2011-08-28 12:11
 */
package se.marell.libusb;

import org.junit.Test;

public class LibUsbSystemTest {
    private static final int VENDOR = 0x10cf;
    private static final int PRODUCT = 0x5500;

    @Test
    public void test() throws Exception {

    }

//  @Test
//  public void testGetDeviceList() throws Exception {
//    UsbSystem us = new LibUsbSystem(false);
//    UsbDeviceList deviceList = us.getUsbDeviceList();
//    assertTrue(deviceList.getUsbDevices().size() > 0);
//    for (UsbDevice dev : deviceList.getUsbDevices()) {
//      System.out.println("Device=" + dev);
//    }
//    deviceList.close(true);
//    us.cleanup();
//  }
//
//  @Test
//  public void testOpenClose() throws Exception {
//    UsbSystem us = new LibUsbSystem(false);
//    UsbDeviceList deviceList = us.getUsbDeviceList();
//    for (UsbDevice dev : deviceList.getUsbDevices()) {
//      try {
//        dev.open();
//        libusb_device_descriptor d = dev.get_descriptor();
//        System.out.print(d);
//        if (d.idVendor == VENDOR && d.idProduct == PRODUCT) {
//          System.out.println(" K8055");
//        } else {
//          System.out.println(" Not K8055");
//        }
//        dev.close();
//      } catch (LibUsbPermissionException e) {
//        // ok
//      }
//    }
//    deviceList.close(true);
//    us.cleanup();
//  }
//
//  @Test
//  public void testDeviceDescriptor() throws Exception {
//    UsbSystem us = new LibUsbSystem(false);
//    UsbDeviceList deviceList = us.getUsbDeviceList();
//    for (UsbDevice dev : deviceList.getUsbDevices()) {
//      try {
//        dev.open();
//        libusb_device_descriptor d = dev.get_descriptor();
//        System.out.println("Manufacturer=" + dev.get_string_ascii(d.iManufacturer) +
//                                   " Product=" + dev.get_string_ascii(d.iProduct) +
//                                   " SerialNumber=" + dev.get_string_ascii(d.iSerialNumber)
//        );
//        dev.close();
//      } catch (LibUsbPermissionException e) {
//        // ok
//      }
//    }
//    deviceList.close(true);
//    us.cleanup();
//  }
}
