/*
 * Copyright (c) 2011 Daniel Marell
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package se.marell.libusb;

import java.util.ArrayList;
import java.util.List;

/**
 * Convenience class used to find a USB device based on vendor and product ID and a sequence number.
 */
public class VendorProductVisitor implements UsbSystem.UsbDeviceVisitor {
    private int vendorId;
    private int productId;
    private int deviceNumber;

    public VendorProductVisitor(int vendorId, int productId, int deviceNumber) {
        this.vendorId = vendorId;
        this.productId = productId;
        this.deviceNumber = deviceNumber;
    }

    @Override
    public List<UsbDevice> visitDevices(List<UsbDevice> allDevices) {
        List<UsbDevice> devices = new ArrayList<UsbDevice>();
        int hitCount = deviceNumber;
        for (UsbDevice d : allDevices) {
            if (d.getIdVendor() == vendorId && d.getIdProduct() == productId) {
                if (hitCount == 0) {
                    devices.add(d);
                    return devices;
                }
                hitCount--;
            }
        }
        return devices;
    }
}