<?php 
require_once "connect.php";
$sql="SELECT * FROM loaisanpham  ";
	
class LoaiSP{
	function LoaiSP($idLoaiSP,$TenLoaiSP,$HinhLoaiSP){
		$this->idLoaiSP=$idLoaiSP;
		$this->TenLoaiSP=$TenLoaiSP;
		$this->HinhLoaiSP=$HinhLoaiSP;
	}
}
	$MangLoaiSP= array();
		if (isset($_GET["idLoaiSP"])) {
		$idLoaiSP=$_GET["idLoaiSP"];
		$sql="SELECT * FROM loaisanpham WHERE FIND_IN_SET('$idLoaiSP',idLoaiSP)";
		$dataLoaiSanPham=mysqli_query($connect,$sql);	
		$rowLoaiSanPham=mysqli_fetch_assoc($dataLoaiSanPham);
	}
	$data=mysqli_query($connect,$sql);	
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($MangLoaiSP, new LoaiSP($row["idLoaiSP"],$row["TenLoaiSP"],$row["HinhLoaiSP"]));
	}
	echo json_encode($MangLoaiSP);
 ?>