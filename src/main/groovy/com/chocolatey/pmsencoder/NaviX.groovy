@Typed
package com.chocolatey.pmsencoder

import net.pms.dlna.virtual.VirtualFolder
import net.pms.dlna.DLNAResource

class NaviX extends Logger {
    VirtualFolder getVirtualFolder() {
	return new VirtualFolder("NaviX", "http://navix.turner3d.net/images/logo_navi-x.png")
    }
}
