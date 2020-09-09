UBOOT_KCONFIG_SUPPORT = "1"
inherit resin-u-boot

FILESEXTRAPATHS_append := ":${THISDIR}/files"

# rework meta-resin patch whose context is different now in u-boot v2019.4
SRC_URI_remove = "file://resin-specific-env-integration-kconfig.patch"
SRC_URI += " \
    file://0001-resin-specific-env-integration-kconfig_REWORKED.patch \
    file://0002-rock960-machine-specific-integration-of-resin-envi.patch \
"
