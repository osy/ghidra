/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.app.util.bin.format.elf.relocation;

public enum NDS32_ElfRelocationType implements ElfRelocationType {
	R_NDS32_NONE(0),
	R_NDS32_16_RELA(19),
	R_NDS32_32_RELA(20),
	R_NDS32_9_PCREL_RELA(22),
	R_NDS32_15_PCREL_RELA(23),
	R_NDS32_17_PCREL_RELA(24),
	R_NDS32_25_PCREL_RELA(25),
	R_NDS32_HI20_RELA(26),
	R_NDS32_LO12S3_RELA(27),
	R_NDS32_LO12S2_RELA(28),
	R_NDS32_LO12S1_RELA(29),
	R_NDS32_LO12S0_RELA(30),
	R_NDS32_SDA15S3_RELA(31),
	R_NDS32_SDA15S2_RELA(32),
	R_NDS32_SDA15S1_RELA(33),
	R_NDS32_SDA15S0_RELA(34),
	R_NDS32_GOT20(37),
	R_NDS32_25_PLTREL(38),
	R_NDS32_COPY(39),
	R_NDS32_GLOB_DAT(40),
	R_NDS32_JMP_SLOT(41),
	R_NDS32_RELATIVE(42),
	R_NDS32_GOTOFF(43),
	R_NDS32_GOTPC20(44),
	R_NDS32_GOT_HI20(45),
	R_NDS32_GOT_LO12(46),
	R_NDS32_GOTPC_HI20(47),
	R_NDS32_GOTPC_LO12(48),
	R_NDS32_GOTOFF_HI20(49),
	R_NDS32_GOTOFF_LO12(50),
	R_NDS32_INSN16(51),
	R_NDS32_LABEL(52),
	R_NDS32_LONGCALL1(53),
	R_NDS32_LONGCALL2(54),
	R_NDS32_LONGCALL3(55),
	R_NDS32_LONGJUMP1(56),
	R_NDS32_LONGJUMP2(57),
	R_NDS32_LONGJUMP3(58),
	R_NDS32_LOADSTORE(59),
	R_NDS32_9_FIXED_RELA(60),
	R_NDS32_15_FIXED_RELA(61),
	R_NDS32_17_FIXED_RELA(62),
	R_NDS32_25_FIXED_RELA(63),
	R_NDS32_PLTREL_HI20(64),
	R_NDS32_PLTREL_LO12(65),
	R_NDS32_PLT_GOTREL_HI20(66),
	R_NDS32_PLT_GOTREL_LO12(67),
	R_NDS32_LO12S0_ORI_RELA(72),
	R_NDS32_DWARF2_OP1_RELA(77),
	R_NDS32_DWARF2_OP2_RELA(78),
	R_NDS32_DWARF2_LEB_RELA(79),
	R_NDS32_WORD_9_PCREL_RELA(94),
	R_NDS32_LONGCALL4(107),
	R_NDS32_RELA_NOP_MIX(192),
	R_NDS32_RELA_NOP_MAX(255);

	public final int typeId;

	private NDS32_ElfRelocationType(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public int typeId() {
		return typeId;
	}
}
