/*
 * Created by Daniel Marell 2011-08-28 10:23
 */
package se.marell.libusb;

import com.sun.jna.Pointer;
import org.junit.Test;
import se.marell.libusb.jna.LibUsb;
import se.marell.libusb.jna.libusb_error;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibUsbTest {
    @Test
    public void testInit() throws Exception {
        LibUsb usb;

        try {
            usb = LibUsb.libUsb;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return;
        }
        int rc;

        rc = usb.libusb_init(null);
        assertSuccess(rc);

        usb.libusb_set_debug(null, 0);

        Pointer[] pa = new Pointer[1];
        rc = usb.libusb_get_device_list(null, pa);
        assertSuccess(rc);
        Pointer[] devices = pa[0].getPointerArray(0);
        assertTrue(devices.length > 1);
        Pointer usb_device = devices[0];

        usb.libusb_free_device_list(null, pa[0], 0);

        rc = usb.libusb_get_bus_number(usb_device);
        assertSuccess(rc);

        rc = usb.libusb_get_device_address(usb_device);
        assertSuccess(rc);

        // todo fix

    /*
    rc = usb.libusb_get_max_packet_size(usb_device, 0);
    assertSuccess(rc);

    rc = usb.libusb_get_max_iso_packet_size(usb_device, 0);
    assertSuccess(rc);

    Pointer p = usb.libusb_ref_device(usb_device);
    assertNotNull(p);

    // Aquire twice...
    usb.libusb_unref_device(usb_device);
    usb.libusb_unref_device(usb_device);

    // But only release once
    rc = usb.libusb_open(usb_device, pa);
    assertSuccess(rc);
    Pointer dev_handle = pa[0];

    p = usb.libusb_open_device_with_vid_pid(null, 0x1234, 0x4321);
    assertFailure(rc);

    p = usb.libusb_get_device(dev_handle);

    int[] arr = new int[1];
    rc = usb.libusb_get_configuration(dev_handle, arr);

    rc = usb.libusb_set_configuration(dev_handle, 0);

    rc = usb.libusb_claim_interface(dev_handle, 0);

    rc = usb.libusb_release_interface(dev_handle, 0);

    rc = usb.libusb_set_interface_alt_setting(dev_handle, 0, 0);

    rc = usb.libusb_clear_halt(dev_handle, (byte) 1);

    rc = usb.libusb_reset_device(dev_handle);

    rc = usb.libusb_kernel_driver_active(dev_handle, 0);

    rc = usb.libusb_detach_kernel_driver(dev_handle, 0);

    rc = usb.libusb_attach_kernel_driver(dev_handle, 0);

    libusb_device_descriptor[] desc = new libusb_device_descriptor[1];
    rc = usb.libusb_get_device_descriptor(usb_device, desc);

    rc = usb.libusb_get_string_descriptor_ascii(dev_handle, (byte) 0, new byte[]{1}, 1);
    */

    /*
    Asynchronous device I/O.
    Todo: Implement
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
    */

    /*
    Polling and timing:
    Todo: Implement
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
    */

//    rc = usb.libusb_control_transfer(Pointer dev_handle,
//    byte bmRequestType,
//    byte bRequest,
//    short wValue,
//    short wIndex,
//    byte[] data,
//    short wLength,
//    int timeout);
//
//    rc = usb.libusb_bulk_transfer(Pointer dev_handle,
//    byte endpoint,
//    byte[] data,
//    int length,
//    int[] transferred,
//    int timeout);

//    int[] arr = new int[1];
//    rc = libusb_interrupt_transfer(dev_handle, 0x01, new byte[]{0, 1, 2, 3, 4, 5, 6, 7}, 8, arr, 1);


/*    usb.libusb_close(dev_handle);

    usb.libusb_exit(null);
    */
    }

    private void assertSuccess(int rc) {
        String s = libusb_error.getText(rc);
        assertFalse("rc=" + s, rc < 0);
    }

    private void assertFailure(int rc) {
        String s = libusb_error.getText(rc);
        assertTrue("rc=" + s, rc < 0);
    }
}
