## libusb10j

``libusb10j`` enables libusb 1.0 from within Java using the JNA (Java Native Access) library.
It is a thin Java-layer on top of [libusb 1.0](http://www.libusb.org/wiki/libusb-1.0).

[API description](http://libusb.sourceforge.net/api-1.0/index.html)

The library is packaged as an OSGi bundle.

### Release notes
* Version 1.0.3 - 2015-07-07  Moved to Github
* Version 1.0.2 - 2014-02-08
  * Java 7
  * Changed pom versioning mechanism.
  * Extended site information.
  * Updated versions of dependencies
* Version 1.0 - 2011-10-25
  * First version. Lacks implementation of functions for Asynchronous device IO and Polling and timing listed below:
```
Asynchronous device I/O.
struct libusb_transfer * 	libusb_alloc_transfer (int iso_packets)
void 	libusb_free_transfer (struct libusb_transfer *transfer)
int 	libusb_submit_transfer (struct libusb_transfer *transfer)
int 	libusb_cancel_transfer (struct libusb_transfer *transfer)
static unsigned char * 	libusb_control_transfer_get_data (struct libusb_transfer *transfer)
static struct libusb_control_setup * 	libusb_control_transfer_get_setup (struct libusb_transfer *transfer)
static void 	libusb_fill_control_setup (unsigned char *buffer, uint8_t bmRequestType, uint8_t bRequest, uint16_t wValue, uint16_t wIndex, uint16_t wLength)
static void 	libusb_fill_control_transfer (struct libusb_transfer *transfer, libusb_device_handle *dev_handle, unsigned char *buffer, libusb_transfer_cb_fn callback, void *user_data, unsigned int timeout)
static void 	libusb_fill_bulk_transfer (struct libusb_transfer *transfer, libusb_device_handle *dev_handle, unsigned char endpoint, unsigned char *buffer, int length, libusb_transfer_cb_fn callback, void *user_data, unsigned int timeout)
static void 	libusb_fill_interrupt_transfer (struct libusb_transfer *transfer, libusb_device_handle *dev_handle, unsigned char endpoint, unsigned char *buffer, int length, libusb_transfer_cb_fn callback, void *user_data, unsigned int timeout)
static void 	libusb_fill_iso_transfer (struct libusb_transfer *transfer, libusb_device_handle *dev_handle, unsigned char endpoint, unsigned char *buffer, int length, int num_iso_packets, libusb_transfer_cb_fn callback, void *user_data, unsigned int timeout)
static void 	libusb_set_iso_packet_lengths (struct libusb_transfer *transfer, unsigned int length)
static unsigned char * 	libusb_get_iso_packet_buffer (struct libusb_transfer *transfer, unsigned int packet)
static unsigned char * 	libusb_get_iso_packet_buffer_simple (struct libusb_transfer *transfer, unsigned int packet)

int 	libusb_try_lock_events (libusb_context *ctx)
void 	libusb_lock_events (libusb_context *ctx)
void 	libusb_unlock_events (libusb_context *ctx)
int 	libusb_event_handling_ok (libusb_context *ctx)
int 	libusb_event_handler_active (libusb_context *ctx)
void 	libusb_lock_event_waiters (libusb_context *ctx)
void 	libusb_unlock_event_waiters (libusb_context *ctx)
int 	libusb_wait_for_event (libusb_context *ctx, struct timeval *tv)
int 	libusb_handle_events_timeout (libusb_context *ctx, struct timeval *tv)
int 	libusb_handle_events (libusb_context *ctx)
int 	libusb_handle_events_locked (libusb_context *ctx, struct timeval *tv)
int 	libusb_pollfds_handle_timeouts (libusb_context *ctx)
int 	libusb_get_next_timeout (libusb_context *ctx, struct timeval *tv)
void 	libusb_set_pollfd_notifiers (libusb_context *ctx, libusb_pollfd_added_cb added_cb, libusb_pollfd_removed_cb removed_cb, void *user_data)
struct libusb_pollfd ** 	libusb_get_pollfds (libusb_context *ctx)
```

### libusb 1.0 installation on Ubuntu Linux

```
$ sudo apt-get install libusb-1.0-0-dev

$ vi /etc/udev/rules.d/95-libusb.rules
SUBSYSTEM=="usb", ACTION=="add", SYSFS{idVendor}=="045e", SYSFS{idProduct}=="00cb", MODE="0777"
```

where ``045e`` and ``00cb`` represents vendor-id and product-id for the USB-device you intend to use.

### Maven usage

```
<repositories>
  <repository>
    <id>marell</id>
    <url>http://marell.se/nexus/content/repositories/releases/</url>
  </repository>
</repositories>
...
<dependency>
  <groupId>se.marell</groupId>
  <artifactId>libusb10j</artifactId>
  <version>1.0.2</version>
</dependency>
```

### Usage example: List USB devices

```
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
```
