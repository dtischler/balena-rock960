DESCRIPTION = "96Boards Rock960 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS = "bison-native rkbin"

SRC_URI = " \
    git://github.com/u-boot/u-boot \
    file://rock960.patch \
"
SRCREV = "v2019.04"

S = "${WORKDIR}/git"

do_compile_append() {
    tools/mkimage -n rk3399 -T rksd -d ${DEPLOY_DIR_IMAGE}/rkbin/rk3399_ddr_800MHz_v1.14.bin idbloader.bin
    cat ${DEPLOY_DIR_IMAGE}/rkbin/rk3399_miniloader_v1.15.bin >> idbloader.bin
    ${DEPLOY_DIR_IMAGE}/rkbin/tools/loaderimage --pack --uboot ./u-boot-dtb.bin uboot.img 0x200000
}

do_install_append() {
    install -d ${D}/boot
    install -c -m 0644 ${B}/idbloader.bin ${B}/uboot.img ${D}/boot
}

do_deploy_append() {
    install ${B}/idbloader.bin ${DEPLOYDIR}
    install ${B}/uboot.img ${DEPLOYDIR}
}
