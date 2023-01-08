package persistence

import (
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/adaptor/out/persistence/entity"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application/port/out"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"
	"gorm.io/gorm"
)

type MemberPersistenceAdaptor struct {
	db *gorm.DB
}

func NewMemberPersistenceAdaptor(db *gorm.DB) *MemberPersistenceAdaptor {
	return &MemberPersistenceAdaptor{
		db: db,
	}
}

var _ out.MemberSaver = (*MemberPersistenceAdaptor)(nil)
var _ out.MemberFinder = (*MemberPersistenceAdaptor)(nil)
var _ out.MemberUpdater = (*MemberPersistenceAdaptor)(nil)
var _ out.MemberDeleter = (*MemberPersistenceAdaptor)(nil)

func (a MemberPersistenceAdaptor) Save(member domain.Member) (vo.MemberId, error) {
	return a.save(member)
}

func (a MemberPersistenceAdaptor) save(member domain.Member) (vo.MemberId, error) {
	e := entity.NewMember(member)
	if err := a.db.Save(e).Error; err != nil {
		return vo.EmptyMemberId, err
	}
	return vo.MemberId(e.Id), nil
}

func (a MemberPersistenceAdaptor) FindById(id vo.MemberId) (*domain.Member, error) {
	return convertErrorWithValue(a.findById(id))
}

func (a MemberPersistenceAdaptor) findById(id vo.MemberId) (*domain.Member, error) {
	var e entity.Member
	if err := a.db.First(&e, id).Error; err != nil {
		return nil, err
	}
	return e.MapToDomain(), nil
}

func (a MemberPersistenceAdaptor) Update(id vo.MemberId, member domain.Member) error {
	return convertError(a.update(id, member))
}

func (a MemberPersistenceAdaptor) update(id vo.MemberId, member domain.Member) error {
	var saved entity.Member
	if err := a.db.First(&saved, id).Error; err != nil {
		return err
	}

	saved.Update(member)
	if err := a.db.Updates(&saved).Error; err != nil {
		return err
	}

	return nil
}

func (a MemberPersistenceAdaptor) Delete(id vo.MemberId) error {
	return a.delete(id)
}

func (a MemberPersistenceAdaptor) delete(id vo.MemberId) error {
	if err := a.db.Delete(&entity.Member{}, id).Error; err != nil {
		return err
	}
	return nil
}
