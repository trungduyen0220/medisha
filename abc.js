	$(document).ready(
	
	
					function() {
						var availableTags = [
								"Zyrtec(10mg)",
								"Efferalgan(500mg)",
								"Eugica(10 vỉ x 10 viên)",
								"Kẹo ngậm ho không đường vị cam Zuckerfrei Pulmoll (50g/hộp)",
								"Viên bổ não Blackmores Brain Active (Lọ 30 viên)",
								"Bột khử mùi Trapha (30g)",
								"Khăn ướt cho bé Pharmacity (80 tờ/gói)",
								"Kem tẩy lông cho da nhạy cảm Cléo (25g)",
								"Viên dưỡng tâm an thần Trasleepy ( 20 Viên )",
								"Viên uống hỗ trợ xương khớp JEX",
								"Trà thảo mộc giảm cân Lanui",
								"Dung dịch bù nước và điện giải Oresol Cam Phúc Vinh",
								"Dung dịch vệ sinh phụ nữ Lactacyd",
								"Dầu gội Thái Dương 3",
								"Kem bôi mờ sẹo thâm Acnes",
								"Diphenhydramine 10mg/1ml",
								"Pediakid - Vitamin D3", "Bonioxy",
								"HAPPY KIDS", "Ruby", "Rabeolone" ];

						$(document).on("focus",
										'#table tr:last-child td:last-child',
										function() {
											var table = $("#table");
											table
													.append('<tr>\
              <td><div contenteditable class="tags"></div></td>\
              <td><div contenteditable></div></td>\
              <td><div contenteditable><div class="custom-select" style="width:50px;"><select><option value="0">Vỉ</option><option value="1">Viên</option><option value="2">Gói</option><option value="3">Hộp</option></select></div></div></td>\
              \</tr>');

											$('body').find(".tags")
													.autocomplete({
														source : availableTags
													});

										});

						$(function() {
							$('body').find(".tags").autocomplete({
								source : availableTags
							});
						});
					});
					
$( "#button-search" ).click(function() {
  alert( "Handler for .click() called." );
});

