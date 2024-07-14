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

import java.util.Map;
import ghidra.app.util.bin.format.elf.ElfConstants;
import ghidra.app.util.bin.format.elf.ElfHeader;
import ghidra.app.util.bin.format.elf.ElfLoadHelper;
import ghidra.app.util.bin.format.elf.ElfRelocation;
import ghidra.app.util.bin.format.elf.ElfRelocationTable;
import ghidra.app.util.bin.format.elf.ElfSymbol;
import ghidra.app.util.importer.MessageLog;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressOutOfBoundsException;
import ghidra.program.model.listing.Program;
import ghidra.program.model.mem.Memory;
import ghidra.program.model.mem.MemoryAccessException;
import ghidra.program.model.reloc.Relocation.Status;
import ghidra.program.model.reloc.RelocationResult;

public class NDS32_ElfRelocationHandler
		extends AbstractElfRelocationHandler<NDS32_ElfRelocationType, ElfRelocationContext<?>> {
	/**
	 * Constructor
	 */
	public NDS32_ElfRelocationHandler() {
		super(NDS32_ElfRelocationType.class);
	}

	@Override
	public boolean canRelocate(ElfHeader elf) {
		return elf.e_machine() == ElfConstants.EM_NDS32;
	}

	@Override
	protected RelocationResult relocate(ElfRelocationContext<?> elfRelocationContext,
			ElfRelocation relocation, NDS32_ElfRelocationType type, Address relocationAddress,
			ElfSymbol sym, Address symbolAddr, long symbolValue, String symbolName)
			throws MemoryAccessException {
		ElfHeader elf = elfRelocationContext.getElfHeader();

		if (elf.e_machine() != ElfConstants.EM_NDS32) {
			return RelocationResult.FAILURE;
		}
		
		if (!elf.is32Bit()) {
			return RelocationResult.FAILURE;
		}

		return doRelocate(elfRelocationContext, type, symbolValue, symbolName, relocation, relocationAddress);
	}

	private RelocationResult doRelocate(ElfRelocationContext<?> elfRelocationContext, NDS32_ElfRelocationType type,
			long symbolValue, String symbolName, ElfRelocation relocation, Address relocationAddress)
			throws MemoryAccessException, AddressOutOfBoundsException {
		Program program = elfRelocationContext.getProgram();
		Memory memory = program.getMemory();
		MessageLog log = elfRelocationContext.getLog();

		// Read instruction as big endian
		int oldValue = memory.getInt(relocationAddress, true);
		
		long addend = 0;
		if(relocation.hasAddend()) {
			addend = relocation.getAddend();
		}
		
		int value = 0;
		int newValue = 0;

		switch (type) {
		case R_NDS32_HI20_RELA:
			value = (int)(symbolValue + addend);
			newValue = (oldValue & 0xfff00000) | (value >> 12);
			memory.setInt(relocationAddress, newValue, true);
			break;
		case R_NDS32_LO12S0_RELA:
			value = (int)(symbolValue + addend);
			newValue = (oldValue & 0xfffff000) | (value & 0xfff);
			memory.setInt(relocationAddress, newValue, true);
			break;
		default:
			markAsUnhandled(program, relocationAddress, type, relocation.getSymbolIndex(), symbolName, log);
			return RelocationResult.UNSUPPORTED;
		}
		return new RelocationResult(Status.APPLIED, 4);
	}
}
