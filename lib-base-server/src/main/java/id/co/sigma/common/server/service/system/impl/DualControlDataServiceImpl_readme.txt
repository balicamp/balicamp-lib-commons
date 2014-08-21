Selamat datang rekan developer. 
===========================================================================
Authod : Gede Sutarsa
Balicamp - 2013

Purpose : 
1. proses seragam untuk approve
2. proses seragam untuk reject
3. proses seragam untuk save for approval


Mohon di perhatikan berikut ini:
============================================================================
1. data dual control di simpan dalam table : m_dual_control_table(mapping JPA ada di class id.co.sigma.common.data.app.CommonDualControlContainerTable)
ini di simpan tanpa reference nya 
misal nya : 
class : branch ada reference ke branch(parent branch) nama parent branch tidak akan di simpan. kalau client code memerlukan data ini, anda perlu bind data manual. yang har 